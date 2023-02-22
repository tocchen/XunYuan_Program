package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import top.tocchen.entity.CVEntity;
import top.tocchen.enums.UserCVUpdateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import top.tocchen.service.ICVService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.redis.CacheKeyName;
import top.tocchen.utils.redis.RedisCacheKeyGenerator;
import top.tocchen.utils.redis.RedisDBName;

import java.util.concurrent.TimeUnit;

/**
 * @author tocchen
 * @date 2023/2/20 14:18
 * @since jdk 1.8
 **/
@Service
public class ICVServiceImpl implements ICVService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public String saveCV(CVEntity entity) {
        CVEntity insert = mongoTemplate.insert(entity);
        String key = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_USER_CV_NAME, insert.getId());
        BoundValueOperations boundValueOps = redisTemplate.boundValueOps(key);
        boundValueOps.set(insert);
        boundValueOps.expire(1, TimeUnit.DAYS);
        return insert.getId();
    }

    public <E> Long  updateCVById(UserCVUpdateType type, E data,String id) {
        Update update = new Update();
        update.set(type.getValue(),data);
        UpdateResult updateResult = mongoTemplate.upsert(new Query(Criteria.where("_id").is(id).and("deleted").is(0)), update, CVEntity.class);
        if (updateResult.getModifiedCount() == 1){
            String key = RedisCacheKeyGenerator.generatorByIdKey(RedisDBName.REDIS_USER_CV_NAME, id);
            redisTemplate.delete(key);
        }
        return updateResult.getModifiedCount();
    }

    @Cacheable(cacheNames = RedisDBName.REDIS_USER_CV_NAME,keyGenerator = CacheKeyName.QUERY_ID_KEY)
    public CVEntity queryCVById(String id){
        Query query = new Query(Criteria.where("_id").is(id).and("deleted").is(0));
        CVEntity result = mongoTemplate.findOne(query, CVEntity.class);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }
    @CacheEvict(cacheNames = RedisDBName.REDIS_USER_CV_NAME,keyGenerator = CacheKeyName.QUERY_ID_KEY)
    public Long removeCVById(String id){
        Update update = new Update();
        update.set("deleted",1);
        UpdateResult updateResult = mongoTemplate.upsert(new Query(Criteria.where("_id").is(id).and("deleted").is(0)), update, CVEntity.class);
        return updateResult.getModifiedCount();
    }

}
