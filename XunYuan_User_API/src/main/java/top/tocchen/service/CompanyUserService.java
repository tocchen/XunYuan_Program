package top.tocchen.service;

import top.tocchen.entity.CompanyUser;
import top.tocchen.vo.CompanyUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 21:21
 * @since jdk 1.8
 **/
public interface CompanyUserService {

    /**
     * 添加AdminUser
     * @param companyUser 实体类
     */
    void insertCompanyUser(CompanyUserVo companyUser);

    /**
     * 通过queryAdminByPhone查询用户
     * @param companyUser 实体类
     * @return 结果
     */
    CompanyUser queryCompanyUserByPhone(CompanyUserVo companyUser);


}
