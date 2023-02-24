package top.tocchen.service;

import top.tocchen.entity.AdminUser;
import top.tocchen.vo.AdminUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 20:19
 * @since jdk 1.8
 **/
public interface AdminUserSerivce {

    /**
     * 添加AdminUser
     * @param adminUser 实体类
     */
    void insertAdminUser(AdminUserVo adminUser);

    /**
     * 通过AccountAndJobNumber查询用户
     * @param adminUserVo 实体类
     * @return 结果
     */
    AdminUser queryAdminByAccountAndJobNumber(AdminUserVo adminUserVo);

}
