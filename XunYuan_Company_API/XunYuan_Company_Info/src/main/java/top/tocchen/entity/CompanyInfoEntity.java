package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/19 16:34
 * @since jdk 1.8
 **/
@Document("xunyuan_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CompanyInfoEntity {
    @Id
    private String id;
    /** 名称 */
    private String companyName;
    /** 介绍 */
    private String introduction;
    /** 工商信息ID */
    private String businessId;
    /** 地址 */
    private String address;
    /** 创建时间 */
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date createDateTime ;
    /** 更新时间  */
    @JsonFormat( pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date updateDateTime ;
    /** 逻辑删除字段 0未删除 1删除 */
    private int deleted = 0;
}
