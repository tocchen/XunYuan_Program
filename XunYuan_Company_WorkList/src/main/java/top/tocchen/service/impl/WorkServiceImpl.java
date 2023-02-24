package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.tocchen.entity.WorkEntity;
import top.tocchen.service.WorkService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.exceptions.UpdateException;
import top.tocchen.utils.redis.CacheKeyName;
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static top.tocchen.utils.redis.RedisDBName.*;

/**
 * @author tocchen
 * @date 2023/2/20 11:53
 * @since jdk 1.8
 **/
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    public void addWork(WorkEntity entity) {
        entity.setUpdateDateTime(new Date());
        entity.setCreateDateTime(new Date());
        WorkEntity insert = mongoTemplate.insert(entity);
        String key = RedisCacheKeyGenerator.generatorByIdKey(REDIS_WORK_LIST_NAME, entity.getId());
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(insert);
        boundValueOperations.expire(1, TimeUnit.DAYS);
    }

    public Long removeWorkByIdAndComId(String id, String companyId) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("_id").is(id),
                Criteria.where("companyId").is(companyId),
                Criteria.where("deleted").is(0)
        );
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("deleted",1);
        update.set("updateDateTime",new Date());
        UpdateResult upset = mongoTemplate.upsert(query, update, WorkEntity.class);
        if (ObjectUtils.isEmpty(upset) || upset.getModifiedCount() != 1){
            throw new ExecuteException();
        }
        String key = RedisCacheKeyGenerator.generatorByIdKey(REDIS_WORK_LIST_NAME, id);
        redisTemplate.delete(key);
        return upset.getModifiedCount();
    }

    public Long updateWorkById(WorkEntity entity) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("_id").is(entity.getId()),
                Criteria.where("companyId").is(entity.getCompanyId()),
                Criteria.where("deleted").is(0)
        );
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("describe",entity.getDescribe());
        update.set("JD",entity.getJD());
        update.set("updateDateTime",new Date());
        update.set("workName",entity.getWorkName());
        update.set("workTags",entity.getWorkTags());
        update.set("workAddress",entity.getWorkAddress());
        update.set("startWorkSalary",entity.getStartWorkSalary());
        update.set("endWorkSalary",entity.getEndWorkSalary());
        update.set("educational",entity.getEducational());
        update.set("workType",entity.getWorkType());
        UpdateResult upset = mongoTemplate.upsert(query, update, WorkEntity.class);

        if (ObjectUtils.isEmpty(upset) || upset.getModifiedCount() != 1){
            throw new UpdateException();
        }
        String key = RedisCacheKeyGenerator.generatorByIdKey(REDIS_WORK_LIST_NAME, entity.getId());
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        entity.setUpdateDateTime(new Date());
        boundValueOperations.set(entity);
        boundValueOperations.expire(1,TimeUnit.DAYS);
        return upset.getModifiedCount();
    }

    @Cacheable(cacheNames = REDIS_WORK_LIST_NAME,keyGenerator = CacheKeyName.QUERY_ID_KEY)
    public WorkEntity queryWorkById(String id) {
        WorkEntity result = mongoTemplate.findById(id, WorkEntity.class);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }

    public List<WorkEntity> queryLatestWorkPage(int current, int pageSize) {
        List<WorkEntity> result = null;
        String key = RedisCacheKeyGenerator.generatorAllKey("queryLatestWorkPage", REDIS_WORK_LIST_NAME, current, pageSize);
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        List range = boundListOperations.range(0, -1);
        if (range != null && range.size() != 0){
            result = new ArrayList<WorkEntity>(range.size());
            for (Object item : range){
                result.add((WorkEntity)redisTemplate.boundValueOps(item).get());
            }
        }else {
            Criteria criteria = Criteria.where("deleted").is(0);
            Sort sort = Sort.by(Sort.Direction.DESC, "updateDateTime");
            Query query = new Query(criteria);
            query.skip((current-1)*pageSize).limit(pageSize);
            query.with(sort);
            result = mongoTemplate.find(query, WorkEntity.class);
            for(WorkEntity item : result){
                String valueKey = RedisCacheKeyGenerator.generatorByIdKey(REDIS_WORK_LIST_NAME, item.getId());
                boundListOperations.rightPush(valueKey);
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(valueKey);
                boundValueOperations.set(item);
                boundValueOperations.expire(1,TimeUnit.DAYS);
            }
        }
        boundListOperations.expire(10,TimeUnit.MINUTES);

        return result;
    }

    public List<WorkEntity> queryWorkByParamPage(String param, int current, int pageSize) {
        List<WorkEntity> result = null;
        String queryCompanyByNameKey = RedisCacheKeyGenerator.generatorParamKeyGenerator("queryWorkByParamPage", REDIS_WORK_LIST_NAME, param,current,pageSize);
        BoundListOperations boundListOperations = redisTemplate.boundListOps(queryCompanyByNameKey);
        List range = boundListOperations.range(0, -1);
        if (range != null && range.size() != 0) {
            result = new ArrayList<WorkEntity>(range.size());
            for (Object o : range) {
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(o);
                WorkEntity entity = (WorkEntity) boundValueOperations.get();
                result.add(entity);
            }
        }else {
            String regex = String.format("%s%s%s","^.*",param,".*$");
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Criteria criteria = new Criteria();
            criteria.orOperator(
                    Criteria.where("workName").regex(pattern),
                    Criteria.where("workTags").regex(pattern),
                    Criteria.where("JD").regex(pattern)
            );
            Query query = new Query(criteria);
            query.skip((current-1)*pageSize).limit(pageSize);
            result = mongoTemplate.find(query, WorkEntity.class);
            for (WorkEntity item:result){
                String valueKey = RedisCacheKeyGenerator.generatorByIdKey(REDIS_WORK_LIST_NAME, item.getId());
                boundListOperations.rightPush(valueKey);
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(valueKey);
                boundValueOperations.set(item);
                boundValueOperations.expire(1,TimeUnit.DAYS);
            }
        }
        boundListOperations.expire(10,TimeUnit.MINUTES);

        return result;
    }

    public List<WorkEntity> queryWorkByScreeningParam(String address, double startWorkSalary, double endWorkSalary, String educational, String workType, int current, int pageSize) {
        Criteria criteria = new Criteria();
        if (!(address == null||"".equals(address))){
            criteria.andOperator(
                    Criteria.where("workAddress").is(address)
            );
        }
        if (!(endWorkSalary==0)){
            criteria.andOperator(
                    Criteria.where("startWorkSalary").gte(startWorkSalary),
                    Criteria.where("endWorkSalary").lte(endWorkSalary)
            );
        }
        if (!(educational == null||"".equals(educational))){
            criteria.andOperator(
                    Criteria.where("educational").is(educational)
            );
        }

        if (!(workType == null||"".equals(workType))){
            criteria.andOperator(
                    Criteria.where("workType").is(workType)
            );
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "updateDateTime");
        Query query = new Query(criteria);
        query.skip((current-1)*pageSize).limit(pageSize);
        query.with(sort);
        return mongoTemplate.find(query, WorkEntity.class);
    }

}
