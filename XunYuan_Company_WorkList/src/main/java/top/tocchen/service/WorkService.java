package top.tocchen.service;

import top.tocchen.entity.WorkEntity;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 11:53
 * @since jdk 1.8
 **/
public interface WorkService {

    /**
     * 添加 工作
     * @param entity top.tocchen.entity
     */
    void addWork(WorkEntity entity);

    /**
     * 删除工作 通过ID 和 CompanyId字段
     */
    Long removeWorkByIdAndComId(String id,String companyId);

    /**
     * 更新工作信息
     * @param entity top.tocchen.entity
     * @return 1代表成功
     */
    Long updateWorkById(WorkEntity entity);

    /**
     * 通过id字段查询工作
     * @param id id
     * @return 返回实体类
     */
    WorkEntity queryWorkById(String id);

    /**
     * 获得最新工作
     * @param current 当前页
     * @param pageSize 页大小
     * @return 返回集合
     */
    List<WorkEntity> queryLatestWorkPage(int current,int pageSize);

    /**
     * 模糊查询 通过工作名称 工作标签 等
     * @param param 条件
     * @param current 当前页
     * @param pageSize 页大小
     * @return 返回集合
     */
    List<WorkEntity> queryWorkByParamPage(String param,int current,int pageSize);

    /**
     * 通过地址查询工作
     * @param address 地址
     * @param educational 学历
     * @param startWorkSalary 工薪
     * @param endWorkSalary 工薪
     * @param workType 工作类型
     * @param current 当前页
     * @param pageSize 页大小
     * @return 返回结果
     */
    List<WorkEntity> queryWorkByScreeningParam(String address,double startWorkSalary,double endWorkSalary,String educational,String workType,int current,int pageSize);

}
