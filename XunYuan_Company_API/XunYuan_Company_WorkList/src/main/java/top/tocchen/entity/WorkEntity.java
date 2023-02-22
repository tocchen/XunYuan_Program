package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 11:48
 * @since jdk 1.8
 **/
@Data
@Document("xunyuan_company_work")
public class WorkEntity implements Serializable {

    private static final long serialVersionUID = 202302201155L;

    @Id
    private String id;

    private String companyId;

    private String workName;

    private String workTags;
    // =========================

    private Double startWorkSalary;

    private Double endWorkSalary;

    private String Educational;

    private String workAddress;

    private String workType;

    // =========================
    private String JD;

    private String describe;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDateTime;

    private int deleted = 0;
}
