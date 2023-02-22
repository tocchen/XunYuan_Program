package top.tocchen.service.impl;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.tocchen.entity.WorkEntity;
import top.tocchen.service.WorkService;
import top.tocchen.utils.DBUtil;
import top.tocchen.utils.exceptions.ExecuteException;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author tocchen
 * @date 2023/2/20 11:53
 * @since jdk 1.8
 **/
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void addWork(WorkEntity entity) {
        WorkEntity insert = mongoTemplate.insert(entity);
    }

    public Long removeWorkByIdAndComId(String id, String companyId) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("_id").is(id),
                Criteria.where("companyId").is(companyId),
                Criteria.where("deleted").is(0)
        );
        Query query = new Query(criteria);
        Update update = new Update().set("deleted",1);
        UpdateResult upset = mongoTemplate.upsert(query, update, WorkEntity.class);
        if (ObjectUtils.isEmpty(upset)){
            throw new ExecuteException();
        }
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

        if (ObjectUtils.isEmpty(upset)){
            throw new ExecuteException();
        }
        return upset.getModifiedCount();
    }

    public WorkEntity queryWorkById(String id) {
        WorkEntity result = mongoTemplate.findById(id, WorkEntity.class);
        DBUtil.isEmpty2QueryException(result);
        return result;
    }

    public List<WorkEntity> queryLatestWorkPage(int current, int pageSize) {
        Criteria criteria = Criteria.where("deleted").is(0);
        Sort sort = Sort.by(Sort.Direction.DESC, "updateDateTime");
        Query query = new Query(criteria);
        query.skip((current-1)*pageSize).limit(pageSize);
        query.with(sort);
        return mongoTemplate.find(query, WorkEntity.class);
    }

    public List<WorkEntity> queryWorkByParamPage(String param, int current, int pageSize) {
        String regex = String.format("%s%s%s","^.*",param,".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("workName").regex(pattern),
                Criteria.where("workTags").regex(pattern),
                Criteria.where("JD").regex(pattern)
        );
        criteria.and("deleted").is(0);
        Query query = new Query(criteria);
        query.skip((current-1)*pageSize).limit(pageSize);
        return mongoTemplate.find(query,WorkEntity.class);
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
