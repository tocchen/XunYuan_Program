package top.tocchen.service;

import top.tocchen.entity.BusinessEntity;

/**
 * @author tocchen
 * @date 2023/2/19 16:50
 * @since jdk 1.8
 **/
public interface BusinessService {


    /**
     * 保存工商信息 并返回id值
     * @param business 实体类
     * @return id值
     */
    String saveBusiness(BusinessEntity business);

    /**
     * 通过 id 字段 删除工商信息
     * @param id id
     * @return 返回结果 1 代表成功
     */
    Long deletedBusiness(String id);

    /**
     * 通过id查询工商信息
     * @param id id
     * @return 实体类
     */
    BusinessEntity queryBusinessById(String id);

    /**
     * 通过id更新数据
     * @param entity 实体类
     * @return 返回结果 1 代表成功
     */
    Long updateBusinessById(BusinessEntity entity);
}
