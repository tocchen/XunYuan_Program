package top.tocchen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import top.tocchen.entity.BusinessEntity;
import top.tocchen.entity.CompanyInfoEntity;

import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/19 17:37
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInfoVo  {

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

    private BusinessEntity businessEntity;

    public CompanyInfoEntity byCompanyInfoVoGetInfo(){
        return new CompanyInfoEntity(
                this.id,
                this.companyName,
                this.introduction,
                this.businessId,
                this.address,
                this.createDateTime,
                this.updateDateTime,
                this.deleted
        );
    }

    public void setCompanyInfoVoData(CompanyInfoEntity companyInfo,BusinessEntity businessEntity){
        this.id = companyInfo.getId();
        this.companyName = companyInfo.getCompanyName();
        this.introduction = companyInfo.getIntroduction();
        this.businessId = companyInfo.getBusinessId();
        this.address = companyInfo.getAddress();
        this.createDateTime = companyInfo.getCreateDateTime();
        this.updateDateTime = companyInfo.getUpdateDateTime();
        this.businessEntity = businessEntity;
    }
}
