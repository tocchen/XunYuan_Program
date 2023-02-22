package top.tocchen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 14:24
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 202302201428L;

    private String name;

    /** 求职状态 */
    private String jobStatus;

    /** 性别： 男 或 女 */
    private String gender;

    /** 身份：学生 无业 在职 */
    private String identity;

    /** 生日 */
    private Date Birthday;

    private String phone;

    private String wechatId;

    private String email;
}
