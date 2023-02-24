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
@EqualsAndHashCode
@ToString
public class CompanyUser implements Serializable {
    private static final long serialVersionUID = 1449048119052529132L;

    private String id;
    /** 公司名称 */
    private String companyName;
    /** 公司联系电话 */
    private String companyUserPhone;
    /** 公司负责人名称 */
    private String companyUserName;
    /** 加密盐 */
    private String companyUserSalt;

    private String sign;

    /** 逻辑删除 */
    private int deleted;

    private String password;

    private Date createTime;

    private Date updateTime;

    public CompanyUser(String id,String companyName,String sign, String companyUserPhone, String companyUserName, @NotNull Map<Object,String> md5Map) {
        this.id = id;
        this.sign = sign;
        this.companyName = companyName;
        this.companyUserPhone = companyUserPhone;
        this.companyUserName = companyUserName;
        this.companyUserSalt = md5Map.get(MD5KeyEnum.SALT);
        this.password = md5Map.get(MD5KeyEnum.MD5_STR);
    }
}
