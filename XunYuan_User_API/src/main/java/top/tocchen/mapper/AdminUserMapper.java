package top.tocchen.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.tocchen.entity.AdminUser;

/**
 * @author tocchen
 * @date 2023/2/9 21:05
 * @since jdk 1.8
 * Implement User CURD
 **/
@Component
public interface AdminUserMapper {

    /**
     * 添加用户
     * @param adminUser 实体类
     */
    void insertAdminUser(AdminUser adminUser);

    /**
     * 查询通过工号判断用户是否存在
     * @param jobNumber 工号
     * @return 非0表示其存在
     */
    int existAdminUserByJobNumber(String jobNumber);

    /**
     * 通过账户 (邮箱或电话) 与 工号查询用户信息
     * @param account 账号
     * @param jobNumber 工号
     * @return 查询的结果
     */
    AdminUser queryAdminUserByAccountAndJobNumber(@Param("account") String account,@Param("jobNumber") String jobNumber);
}
