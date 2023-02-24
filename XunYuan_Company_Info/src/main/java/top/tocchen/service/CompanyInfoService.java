package top.tocchen.service;

import top.tocchen.entity.CompanyInfoEntity;
import top.tocchen.vo.CompanyInfoVo;

import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/19 16:49
 * @since jdk 1.8
 **/
public interface CompanyInfoService {

    /**
     * 保存企业信息
     * @param companyInfoVo 实体类对象
     */
    void saveCompanyInfo(CompanyInfoVo companyInfoVo);

    /**
     * 删除企业信息 通过Id字段
     * @param id Id字段
     * @param companyUserId 企业用户id
     * @return 操作结果
     */
    boolean deletedCompanyInfoById(String id,String companyUserId);

    /**
     * 通过企业用户id字段查询信息
     * @param companyUserId id
     * @return 结果
     */
    CompanyInfoEntity queryCompanyByUserId(String companyUserId);

    /**
     * 通过Id字段更新企业
     * @param entity entity
     * @return 结果
     */
    boolean updateCompanyInfoById(CompanyInfoEntity entity);

    /**
     * 通过企业名称查询企业
     * @param name 企业名称
     * @return 返回结果集
     */
    List<CompanyInfoEntity> queryCompanyByName(String name);
}
