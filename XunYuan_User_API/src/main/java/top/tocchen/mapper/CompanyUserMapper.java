package top.tocchen.mapper;

import org.apache.ibatis.annotations.Param;
import top.tocchen.entity.CompanyUser;

/**
 * @author tocchen
 * @date 2023/2/23 19:49
 * @since jdk 1.8
 **/
public interface CompanyUserMapper {
    /**
     * 添加用户
     * @param adminUser 实体类
     */
    void insertCompanyUser(CompanyUser adminUser);

    /**
     * 查询通过企业名称判断用户是否存在
     * @param companyName 企业名称
     * @return 非0表示其存在
     */
    int existCompanyUserByCompanyName(String companyName);

    /**
     * 通过联系电话查询用户信息
     * @param companyUserPhone 联系电话
     * @return 查询的结果
     */
    CompanyUser queryCompanyUserByPhone(@Param("userPhone") String companyUserPhone);
}