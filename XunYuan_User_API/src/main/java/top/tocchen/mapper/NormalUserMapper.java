package top.tocchen.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.tocchen.entity.NormalUser;

/**
 * @author tocchen
 * @date 2023/2/23 19:48
 * @since jdk 1.8
 **/
@Component
public interface NormalUserMapper {

    /**
     * 添加用户
     * @param normalUser 实体类
     */
    void insertNormalUser(NormalUser normalUser);

    /**
     * 查询通过企业名称判断用户是否存在
     * @param phone 企业名称
     * @return 非0表示其存在
     */
    int existNormalUserByPhone(String phone);

    /**
     * 通过联系电话查询用户信息
     * @param phoneOrAccount 联系电话 或 账号
     * @return 查询的结果
     */
    NormalUser queryNormalUserByPhoneOrAccount(@Param("params") String phoneOrAccount);

}
