package top.tocchen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.tocchen.entity.CompanyEntity;
import top.tocchen.mapper.CompanyMapper;
import top.tocchen.service.CompanyService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.redis.CacheKeyName;
import top.tocchen.utils.exceptions.DataExistsException;
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tocchen
 * @date 2023/2/11 14:21
 * @since jdk 1.8
 **/
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    // key: queryPageAllCompany[current,pageSize]
    public List<CompanyEntity> queryPageAllCompany(int current, int pageSize) {
        String key = RedisCacheKeyGenerator.generatorAllKey("queryPageAllCompany",RedisDBName.REDIS_COMPANY_NAME, current, pageSize);
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        List range = boundListOperations.range(0, -1);
        List result = null;
        if (range != null && range.size() != 0){
            logger.info("缓存中加载缓存数据");
            result = new ArrayList<CompanyEntity>(range.size());
            for(Object item : range){
                Object tempResult = redisTemplate.boundValueOps(item).get();
                if (tempResult == null){
                    String[] split = item.toString().split("\\[");
                    String id = split[split.length - 1].replace("\\]", "");
                    CompanyEntity companyEntity = this.queryCompanyById(id);
                    result.add(companyEntity);
                }else{
                    if (tempResult instanceof CompanyEntity){
                        result.add(tempResult);
                    }
                }
            }
        }else{
            logger.info("缓存中无法加载数据，执行DB 操作并将其结果缓存");
            result = companyMapper.queryPageAllCompany((current - 1) * pageSize, pageSize);
            for(Object item : result){
                CompanyEntity entity = (CompanyEntity) item;
                String id = entity.getId();
                String itemKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_NAME,id);
                boundListOperations.rightPush(itemKey);
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(itemKey);
                if (boundValueOperations.get() == null || "".equals(boundValueOperations.get())){
                    boundValueOperations.set(entity);
                    boundValueOperations.expire(12,TimeUnit.HOURS);
                }
            }
        }
        boundListOperations.expire(5,TimeUnit.MINUTES);

        return result;
    }

    @Cacheable(cacheNames = RedisDBName.REDIS_COMPANY_NAME,keyGenerator = CacheKeyName.QUERY_ID_KEY)
    public CompanyEntity queryCompanyById(String id) {
        CompanyEntity result = companyMapper.queryCompanyById(id);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }

    @CacheEvict(cacheNames = RedisDBName.REDIS_COMPANY_NAME,keyGenerator = CacheKeyName.REMOVE_ID_KEY)
    public int removeCompanyById(String id) {
        return companyMapper.removeCompanyById(id);
    }

    public int updateCompanyById(CompanyEntity companyEntity) {
        int result = companyMapper.updateCompanyById(companyEntity);
        //不能使用注解方式进行存储-应当使用Redis
        if (result == 1){
            BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_NAME,companyEntity.getId()));
            boundValueOperations.set(companyEntity);
            // 设置TTL为12小时
            boundValueOperations.expire(12, TimeUnit.HOURS);
        }
        return result;
    }

    public int updateCompanyStatusById(String id) {
        int result = companyMapper.updateCompanyStatusById(id);
        if (result == 1){
            String key = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_NAME,id);
            BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
            Object o = boundValueOperations.get();
            if ( !(o instanceof CompanyEntity)){
                logger.error("获取的Redis数据格式不正确!对其缓存进行删除");
                redisTemplate.delete(key);
               return result;
            }
            CompanyEntity companyEntity = (CompanyEntity) o;
            companyEntity.setStatus((companyEntity.getStar()+1)%2);
            companyEntity.setUpdateDatetime(new Date());
            // 缓存
            boundValueOperations.set(companyEntity);
            boundValueOperations.expire(12, TimeUnit.HOURS);
        }
        return result;
    }

    public int updateCompanySignKeyById(String id, String signKey) {
        return companyMapper.updateCompanySignKeyById(id, signKey);
    }


    public void saveCompany(CompanyEntity companyEntity) {
        int i = this.queryCompanyNameExist(companyEntity.getCompanyName());
        if (i != 0){
            throw new DataExistsException();
        }
        companyMapper.saveCompany(companyEntity);
        String key = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_NAME,companyEntity.getId());
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        boundValueOperations.set(companyEntity);
        boundValueOperations.expire(12, TimeUnit.HOURS);
    }


    public List<CompanyEntity> queryCompanyLikeNameCodeAddress(String parameter, int current, int pageSize) {
        String key = RedisCacheKeyGenerator.generatorParamKeyGenerator("queryCompanyLikeNameCodeAddress", RedisDBName.REDIS_COMPANY_NAME,parameter,current,pageSize);
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        List range = boundListOperations.range(0, -1);
        List result = null;
        if (range != null && range.size() != 0){
            logger.info("缓存中加载缓存数据");
            result = new ArrayList(range.size());
            for(Object item : range) {
                Object tempResult = redisTemplate.boundValueOps(item).get();
                if (tempResult == null) {
                    String[] split = item.toString().split("\\[");
                    String id = split[split.length - 1].replace("\\]", "");
                    CompanyEntity companyEntity = this.queryCompanyById(id);
                    result.add(companyEntity);
                } else {
                    if (tempResult instanceof CompanyEntity) {
                        result.add(tempResult);
                    }
                }
            }
        }else{
            logger.info("缓存中无法加载数据，执行DB 操作并将其结果缓存");
            result = companyMapper.queryCompanyLikeNameCodeAddress(parameter,(current-1)*pageSize,pageSize);
            for(Object item : result){
                CompanyEntity entity = (CompanyEntity) item;
                String id = entity.getId();
                String itemKey = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_COMPANY_NAME,id);
                boundListOperations.rightPush(itemKey);
                BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(itemKey);
                if (boundValueOperations.get() == null || "".equals(boundValueOperations.get())){
                    boundValueOperations.set(entity);
                    boundValueOperations.expire(12,TimeUnit.HOURS);
                }
            }
        }
        boundListOperations.expire(5,TimeUnit.MINUTES);
        return result;
    }

    public int queryPageCount(String parameter) {
        String key = RedisCacheKeyGenerator.generatorCountKey("queryPageCount", RedisDBName.REDIS_COMPANY_NAME, parameter);
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        Object value = boundValueOperations.get();
        if ( value != null){
            return (Integer) value;
        }else{
            int result = companyMapper.queryPageCount(parameter);
            boundValueOperations.set(result);
            boundValueOperations.expire(3,TimeUnit.MINUTES);
            return result;
        }

    }

    @Cacheable(cacheNames = RedisDBName.REDIS_COMPANY_NAME,keyGenerator = CacheKeyName.COUNT_KEY)
    public int queryCompanyNameExist(String companyName) {
        return companyMapper.queryCompanyNameExist(companyName);
    }

    public List<CompanyEntity> queryStarCompany() {
        return companyMapper.queryStarCompany();
    }
}
