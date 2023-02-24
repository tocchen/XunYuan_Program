package top.tocchen.service;

import top.tocchen.entity.AdminUser;

/**
 * @author tocchen
 * @date 2023/2/9 21:34
 * @since jdk 1.8
 **/
public interface UserService {

    /**
     * 通过 id 字段查询用户
     * @param id id
     * @return 用户实体类
     */
    AdminUser queryIdByUser(String id);

    /**
     * 通过 工号、邮箱、密码 字段查询用户
     * @param jobNumber 工号
     * @param email 邮箱
     * @param password 密码
     * @return 用户实体类
     */
    AdminUser queryMoreByUser(String jobNumber, String email, String password);

    /**
     * 将 id,jobNumber,Email,Password 存储到数据库当中
     * @param id id
     * @param jobNumber 工号
     * @param email 邮箱
     * @param password 密码
     */
    void saveUser(String id,String jobNumber,String email,String password,String salt);

    /**
     * 通过 id 字段更改对应密码
     * @param id id
     * @param newPassword 新密码
     * @return 返回结果 1 代表更改成功
     */
    int updatePassword(String id,String newPassword);

    /**
     * 通过 id 字段删除对应用户
     * @param id id
     * @return 返回结果 1 代表更改成功
     * 执行逻辑删除
     */
    int removeUser(String id);


    String queryUserSalt(String id);

}
