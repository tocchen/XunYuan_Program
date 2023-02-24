package top.tocchen.entity;

import com.sun.istack.internal.NotNull;
import lombok.*;
import top.tocchen.enums.MD5KeyEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author tocchen
 * @date 2023/2/23 19:49
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class NormalUser implements Serializable {
    private static final long serialVersionUID = -926425214673765246L;

    private String id;

    private String userName;

    private String userAccount;

    private String userPassword;

    private String userSalt;

    private String userPhone;

    private String userEmail;

    private int delete;

    private Date createTime;

    private Date updateTime;


    public NormalUser(String id, String userName, String userAccount, String userPhone, String userEmail, @NotNull Map<Object,String> md5Map) {
        this.id = id;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPassword = md5Map.get(MD5KeyEnum.MD5_STR);
        this.userSalt = md5Map.get(MD5KeyEnum.SALT);
    }
}
