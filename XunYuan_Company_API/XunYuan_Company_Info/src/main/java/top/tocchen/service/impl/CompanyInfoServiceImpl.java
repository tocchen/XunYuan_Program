package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
import top.tocchen.vo.CompanyInfoVo;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author tocchen
 * @date 2023/2/19 17:39
 * @since jdk 1.8
 **/
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BusinessService businessService;

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
    }

    public Long deletedCompanyInfoById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("deleted",1);
        update.set("updateDateTime",new Date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, CompanyInfoEntity.class);
        return updateResult.getModifiedCount();
    }

    public CompanyInfoEntity queryCompanyById(String id) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("_id").is(id),
                Criteria.where("deleted").is(0)
        );
        query.addCriteria(criteria);
        CompanyInfoEntity result = mongoTemplate.findOne(query, CompanyInfoEntity.class);
        DBUtil.isEmpty2QueryException(result);
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
        return updateResult.getModifiedCount();
    }

    public List<CompanyInfoEntity> queryCompanyByName(String name) {
        String regex = String.format("%s%s%s","^.*",name,".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("companyName").regex(pattern),
                Criteria.where("deleted").is(0)
        );
        List<CompanyInfoEntity> result = mongoTemplate.find(new Query(criteria), CompanyInfoEntity.class);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }
}
