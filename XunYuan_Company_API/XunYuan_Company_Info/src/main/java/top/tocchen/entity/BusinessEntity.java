package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/19 16:37
 * @since jdk 1.8
 **/
@Document("xunyuan_business_administration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BusinessEntity {
    @Id
    private String id;
    /** 企业名称 */
    private String enterpriseName;
    /** 法人 */
    private String enterpriseUserName;
    /** 成立时间 */
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date registerDate;
    /** 企业类型 */
    private String enterpriseType;
    /** 经营状态 */
    private String COBStatus;
    /** 注册资金 */
    private double registerMoney;
    /** 注册地址 */
    private String registerAddress;
    /** 统一社会信用代码 */
    private String USCC;
    /** 经验范围 */
    private String OR;
    /** 创建时间 */
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date createDateTime ;
    /** 更新时间  */
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date updateDateTime ;
    /** 逻辑删除字段 0未删除 1删除 */
    private int deleted = 0;

}
