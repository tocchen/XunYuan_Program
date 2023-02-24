package top.tocchen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import top.tocchen.enums.UserRoleEnum;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/22 18:55
 * @since jdk 1.8
 * 登录用户VO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAuthVO implements Serializable {

    private static final long serialVersionUID = 202302221900L;

    private String userId;

    private String userName;

    private String salt;

    private String password;

    private UserRoleEnum role;

    private String verifStr;

    private String othersStr;

}
