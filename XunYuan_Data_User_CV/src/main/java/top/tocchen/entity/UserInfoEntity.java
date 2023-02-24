package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 14:24
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 202302201428L;

    private String name;

    /** 用户Id */
    private String userId;

    /** 求职状态 */
    private String jobStatus;

    /** 性别： 男 或 女 */
    private String gender;

    /** 身份：学生 无业 在职 */
    private String identity;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String phone;

    private String wechatId;

    private String email;
}
