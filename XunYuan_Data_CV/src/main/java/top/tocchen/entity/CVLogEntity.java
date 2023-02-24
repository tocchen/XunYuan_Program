package top.tocchen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 19:31
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
public class CVLogEntity implements Serializable {

    private String id;

    private String companyId;

    private String userId;

    private String cvId;

    private String jobId;

    private Date updateDateTime;

    private Date createDateTime;

    /** 简历状态 0代表未审核 1代表审核通过 -1代表审核不通过 */
    private int cvStatus = 0;

    private int deleted = 0;

}
