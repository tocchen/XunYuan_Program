package top.tocchen.service;

import top.tocchen.entity.CVLogEntity;
import top.tocchen.vo.PageResponse;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 20:24
 * @since jdk 1.8
 **/
public interface CVLogService {

    /**
     * 查询所有记录
     * @return list
     */
    PageResponse<List> queryAll(int current, int pageSize);

    /**
     * 通过公司id查询记录
     * @param companyId 公司id
     * @return list
     */
    PageResponse<List> queryAllByCompanyId(String companyId,int current, int pageSize);

    /**
     * 通过用户id查询记录
     * @param userId 用户id
     * @return list
     */
    PageResponse<List> queryAllByUserId(String userId,int current, int pageSize);

    /**
     * 添加记录
     * @param entity entity
     */
    void insertCVLog(CVLogEntity entity);

    /**
     * 删除记录
     * @param id id值
     * @return 返回结果 1代表成功 0代表失败
     */
    void removeCVLog(String id);

}
