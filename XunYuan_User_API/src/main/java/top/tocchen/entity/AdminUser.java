package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.internal.NotNull;
import lombok.*;
import top.tocchen.enums.MD5KeyEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author tocchen
 * @date 2023/2/9 21:00
 * @since jdk 1.8
 *
 * 账号： 电话-邮箱+工号+密码
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUser implements Serializable {

    static final long serialVersionUID = 202302092101L;

    private String id;

    private String email;

    private String phone;

    private String jobNumber;

    private String password;

    private String userName;

    private String salt;

    private Integer delete;

    private Date createDatetime;

    private Date updateDatetime;


    /**
     * 判断UserEntity实体类基本信息是否完善 [email,jobNumber,password]
     * @param user UserEntity实体类
     * @return 返回判断结果
     */
    public static boolean userBaseComplete(AdminUser user){
        if (user.getEmail() == null || user.getJobNumber() == null || user.getPassword() == null){
            return false;
        }
        return true;
    }

    public AdminUser(String id, String email, String phone, String jobNumber,@NotNull Map<Object,String> md5Map) {
        this.password = md5Map.get(MD5KeyEnum.MD5_STR);
        this.salt = md5Map.get(MD5KeyEnum.SALT);
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.jobNumber = jobNumber;
        this.salt = salt;
    }
}
