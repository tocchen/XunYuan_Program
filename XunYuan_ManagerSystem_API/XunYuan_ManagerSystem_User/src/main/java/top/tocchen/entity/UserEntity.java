package top.tocchen.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/9 21:00
 * @since jdk 1.8
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity implements Serializable {

    static final long serialVersionUID = 202302092101L;

    private String id;

    private String email;

    private String jobNumber;

    private String password;

    private Integer delete;

    private Date createDatetime;

    private Date updateDatetime;


    /**
     * 判断UserEntity实体类基本信息是否完善 [email,jobNumber,password]
     * 在{@link top.tocchen.controller.UserController#userLogin(JSONObject)}方法中体现
     * @param user UserEntity实体类
     * @return 返回判断结果
     */
    public static boolean userBaseComplete(UserEntity user){
        if (user.getEmail() == null || user.getJobNumber() == null || user.getPassword() == null){
            return false;
        }
        return true;
    }

}
