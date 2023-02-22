package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 14:38
 * @since jdk 1.8
 * 教育经历
 **/
@Data
public class UserEducational implements Serializable {
    private static final long serialVersionUID = 202302201440L;
    private String schoolName;
    private String educational;
    private String major;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String onSchoolContent;
}
