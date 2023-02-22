package top.tocchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tocchen.entity.CVLogEntity;
import top.tocchen.mapper.CVLogMapper;
import top.tocchen.service.CVLogService;
import top.tocchen.utils.exceptions.UpdateException;
import top.tocchen.vo.PageResponse;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 20:25
 * @since jdk 1.8
 **/
@Service
public class CVLogServiceImpl implements CVLogService {

    @Autowired
    private CVLogMapper mapper;


    public PageResponse<List> queryAll(int current, int pageSize) {
        List<CVLogEntity> result = mapper.queryAll((current - 1) * pageSize, pageSize);
        int count = mapper.queryAllCount();
        return new PageResponse<List>(result,current,pageSize,count/pageSize,count);
    }

    public PageResponse<List> queryAllByCompanyId(String companyId, int current, int pageSize) {
        List<CVLogEntity> result = mapper.queryAllByCompanyId(companyId, (current - 1) * pageSize, pageSize);
        int count = mapper.queryAllByCompanyIdCount(companyId);
        return new PageResponse<List>(result,current,pageSize,count/pageSize,count);
    }

    public PageResponse<List> queryAllByUserId(String userId, int current, int pageSize) {
        List<CVLogEntity> result = mapper.queryAllByUserId(userId, (current - 1) * pageSize, pageSize);
        int count = mapper.queryAllByUserIdCount(userId);
        return new PageResponse<List>(result,current,pageSize,count/pageSize,count);
    }

    public void insertCVLog(CVLogEntity entity) {
        mapper.insertCVLog(entity);
    }

    public void removeCVLog(String id) {
        int result = mapper.removeCVLog(id);
        if (result != 1){
            throw new UpdateException();
        }
    }
}
