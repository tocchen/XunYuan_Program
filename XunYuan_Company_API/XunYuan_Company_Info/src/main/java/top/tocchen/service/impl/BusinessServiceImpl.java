package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.tocchen.entity.BusinessEntity;
import top.tocchen.entity.CompanyInfoEntity;
import top.tocchen.service.BusinessService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.ExecuteException;
import top.tocchen.utils.exceptions.UpdateException;

import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/19 16:50
 * @since jdk 1.8
 *
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveBusiness(BusinessEntity business) {
        BusinessEntity insertResult = mongoTemplate.insert(business);
        if (ObjectUtils.isEmpty(insertResult)){
            throw new ExecuteException();
        }
        return insertResult.getId();
    }

    public Long deletedBusiness(String id){
        BusinessEntity tmp = mongoTemplate.findById(id, BusinessEntity.class);
        if (ObjectUtils.isEmpty(tmp)){
            throw new UpdateException();
        }
        tmp.setDeleted(1);
        Query query = new Query(Criteria.where("_id").is(tmp.getId()));
        Update update = new Update();
        update.set("deleted",1);
        UpdateResult updateResult = mongoTemplate.upsert(query, update, BusinessEntity.class);
        if (ObjectUtils.isEmpty(updateResult)){
            throw new ExecuteException();
        }
        return updateResult.getModifiedCount();
    }

    public BusinessEntity queryBusinessById(String id) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("_id").is(id),
                Criteria.where("deleted").is(0)
        );
        query.addCriteria(criteria);
        BusinessEntity result = mongoTemplate.findOne(query, BusinessEntity.class);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }

    public Long updateBusinessById(BusinessEntity entity) {
        Query query = new Query(Criteria.where("_id").is(entity.getId()));
        Update update = new Update();
        update.set("enterpriseName",entity.getEnterpriseName());
        update.set("enterpriseUserName",entity.getEnterpriseUserName());
        update.set("registerDate",entity.getRegisterDate());
        update.set("enterpriseType",entity.getEnterpriseType());
        update.set("COBStatus",entity.getCOBStatus());
        update.set("registerMoney",entity.getRegisterMoney());
        update.set("registerAddress",entity.getRegisterAddress());
        update.set("USCC",entity.getUSCC());
        update.set("OR",entity.getOR());
        update.set("updateDateTime",new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, CompanyInfoEntity.class);
        return updateResult.getModifiedCount();
    }
}