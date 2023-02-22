package top.tocchen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.tocchen.entity.UserEntity;

/**
 * @author tocchen
 * @date 2023/2/9 21:05
 * @since jdk 1.8
 * Implement User CURD
 **/
@Component
public interface UserMapper {

    /**
     * 通过 id 字段查询用户
     * @param id id
     * @return 用户实体类
     */
    UserEntity queryIdByUser(String id);

    /**
     * 通过 工号、邮箱、密码 字段查询用户
     * @param jobNumber 工号
     * @param email 邮箱
     * @param password 密码
     * @return 用户实体类
     */
    UserEntity queryMoreByUser(@Param("jobNumber") String jobNumber,@Param("email") String email,@Param("password") String password);

    /**
     * 通过id字段获取账户加密盐Salt
     * @param id id
     * @return 返回Salt
     */
    String queryUserSalt(String id);

    /**
     * 通过 email 与 JobNumber 获取User id
     * @param email 邮箱
     * @param jobNumber 工号
     * @return id值
     */
    String queryEmailJobNumberByUserId(@Param("email") String email,@Param("jobNumber") String jobNumber);

    /**
     * 判断当前工号是否存在
     * @param jobNumber 工号
     * @return
     */
    int queryJobNumberExists(String jobNumber);

    /**
     * 将 id,jobNumber,Email,Password 存储到数据库当中
     * @param id id
     * @param jobNumber 工号
     * @param email 邮箱
     * @param password 密码
     * @param salt 机密盐
     */
    void saveUser(@Param("id") String id,@Param("jobNumber") String jobNumber,
                  @Param("email") String email,@Param("password") String password,
                  @Param("salt") String salt);

    /**
     * 通过 id 字段更改对应密码
     * @param id id
     * @param newPassword 新密码
     * @return 返回结果 1 代表更改成功
     */
    int updatePassword(@Param("id") String id,@Param("newPassword") String newPassword);

    /**
     * 通过 id 字段删除对应用户
     * @param id id
     * @return 返回结果 1 代表更改成功
     * 执行逻辑删除
     */
    int removeUser(String id);
}
