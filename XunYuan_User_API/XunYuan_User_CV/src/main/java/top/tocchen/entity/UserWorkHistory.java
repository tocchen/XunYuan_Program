package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 14:30
 * @since jdk 1.8
 * 工作经历
 **/
@Data
public class UserWorkHistory implements Serializable {

    private static final long serialVersionUID = 202302201433L;

    /** 公司名称 */
    private String companyName;
    /** 部门 */
    private String department;
    /** 位置 */
    private String position;
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    /** 工作内容 */
    private String workContent;
    /** 工作业绩 */
    private String performance;

}
