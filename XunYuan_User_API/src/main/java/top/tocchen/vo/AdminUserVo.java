package top.tocchen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/23 19:58
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AdminUserVo implements Serializable {

    private static final long serialVersionUID = -4313668148679846995L;

    /** 密码 */
    private String password;
    /** 工号 */
    private String jobNumber;
    /** 邮箱 */
    private String email;
    /** 电话 */
    private String phone;

    private String userName;

}
