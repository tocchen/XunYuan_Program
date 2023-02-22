package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import top.tocchen.entity.BusinessEntity;
import top.tocchen.entity.CompanyInfoEntity;
import top.tocchen.service.BusinessService;
import top.tocchen.service.CompanyInfoService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.exceptions.UpdateException;
import top.tocchen.utils.redis.CacheKeyName;
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;
import top.tocchen.vo.CompanyInfoVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author tocchen
 * @date 2023/2/19 17:39
 * @since jdk 1.8
 **/
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private static final String QUERY_COMPANY_LIST_NAME = "companyInfoList";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = ExecuteException.class)
    public void saveCompanyInfo(CompanyInfoVo companyInfoVo) {
        // ============== Save Business ===================
        BusinessEntity businessEntity = companyInfoVo.getBusinessEntity();
        businessEntity.setCreateDateTime(new Date());
        businessEntity.setUpdateDateTime(new Date());
        String businessId = businessService.saveBusiness(businessEntity);
        if ("".equals(businessId) || businessId == null){
            throw new ExecuteException();
        }
        // ============== Save CompanyInfo =================
        CompanyInfoEntity companyInfoEntity = companyInfoVo.byCompanyInfoVoGetInfo();
        companyInfoEntity.setCreateDateTime(new Date());
        companyInfoEntity.setUpdateDateTime(new Date());
        companyInfoEntity.setBusinessId(businessId);
        CompanyInfoEntity insert = mongoTemplate.insert(companyInfoEntity);
        if (ObjectUtils.isEmpty(insert)){
            throw new ExecuteException();
        }

        String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_INFO_NAME, insert.getId());
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(valueKey);
        boundValueOperations.set(insert);
        boundValueOperations.expire(1, TimeUnit.DAYS);
    }

    @CacheEvict(cacheNames = RedisDBName.REDIS_COMPANY_INFO_NAME,keyGenerator = CacheKeyName.QUERY_ID_KEY)
    public Long deletedCompanyInfoById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("deleted",1);
        update.set("updateDateTime",new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, CompanyInfoEntity.class);

        return updateResult.getModifiedCount();
    }

    public CompanyInfoEntity queryCompanyById(String id) {
        CompanyInfoEntity result = null;
        String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_INFO_NAME, id);
        BoundValueOperations boundValueOps = redisTemplate.boundValueOps(valueKey);
        Object o = boundValueOps.get();
        if (o instanceof CompanyInfoEntity){
            result = (CompanyInfoEntity) o;
        }else{
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.andOperator(
                    Criteria.where("_id").is(id),
                    Criteria.where("deleted").is(0)
            );
            query.addCriteria(criteria);
            result = mongoTemplate.findOne(query, CompanyInfoEntity.class);
            DBUtil.isEmpty2QueryException(result);
            assert result != null;
            boundValueOps.set(result);
            boundValueOps.expire(1, TimeUnit.DAYS);
        }

        return result;
    }

    public Long updateCompanyInfoById(CompanyInfoEntity entity) {
        Query query = new Query(Criteria.where("_id").is(entity.getId()));
        Update update = new Update();
        update.set("companyName",entity.getCompanyName());
        update.set("introduction",entity.getIntroduction());
        update.set("address",entity.getAddress());
        update.set("updateDateTime",new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, CompanyInfoEntity.class);
        if (updateResult.getModifiedCount() == 1){
            String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_INFO_NAME, entity.getId());
            BoundValueOperations boundValueOps = redisTemplate.boundValueOps(valueKey);
            boundValueOps.set(entity);
            boundValueOps.expire(1, TimeUnit.DAYS);
        }
        return updateResult.getModifiedCount();
    }

    public List<CompanyInfoEntity> queryCompanyByName(String name) {
        List<CompanyInfoEntity> result = null;
        String queryCompanyByNameKey = RedisCacheKeyGenerator.generatorParamKeyGenerator("queryCompanyByName", RedisDBName.REDIS_COMPANY_INFO_NAME, name);
        BoundListOperations boundListOperations = redisTemplate.boundListOps(queryCompanyByNameKey);
        List range = boundListOperations.range(0, -1);
        if (range != null && range.size() != 0){
            result = new ArrayList<CompanyInfoEntity>(range.size());
            for (Object o : range){
                String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_INFO_NAME, (String) o);
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(valueKey);
                CompanyInfoEntity entity = (CompanyInfoEntity) boundValueOperations.get();
                result.add(entity);
            }
        }else{
            String regex = String.format("%s%s%s","^.*",name,".*$");
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Criteria criteria = new Criteria();
            criteria.andOperator(
                    Criteria.where("companyName").regex(pattern),
                    Criteria.where("deleted").is(0)
            );
            result = mongoTemplate.find(new Query(criteria), CompanyInfoEntity.class);
            DBUtil.isEmpty2QueryException(result);
            for (CompanyInfoEntity entity : result){
                String valueKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_INFO_NAME, (String) entity.getId());
                boundListOperations.rightPush(valueKey);
            }
        }

        return result;
    }
}
