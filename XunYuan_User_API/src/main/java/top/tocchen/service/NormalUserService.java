package top.tocchen.service;

import top.tocchen.entity.NormalUser;
import top.tocchen.vo.NormalUserVo;

/**
 * @author tocchen
 * @date 2023/2/23 22:21
 * @since jdk 1.8
 **/
public interface NormalUserService {


    /**
     * 添加AdminUser
     * @param normalUserVo 实体类
     */
    void insertNormalUser(NormalUserVo normalUserVo);

    /**
     * 通过queryAdminByPhone查询用户
     * @param normalUserVo 实体类
     * @return 结果
     */
    NormalUser queryNormalUserByPhoneOrAccount(NormalUserVo normalUserVo);


}
