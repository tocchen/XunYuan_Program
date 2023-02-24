package top.tocchen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/23 21:15
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserVo implements Serializable {
    private static final long serialVersionUID = 2756506250449253846L;

    /** 公司名称 */
    private String companyName;
    /** 公司联系电话 */
    private String companyUserPhone;
    /** 公司负责人名称 */
    private String companyUserName;

    private String password;

    private String sign;

}
