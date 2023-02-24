package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/20 14:35
 * @since jdk 1.8
 * 项目经历
 **/
@Data
@NoArgsConstructor
public class UserProjectHistory implements Serializable {

    private static final long serialVersionUID = 202302201437L;

    private String projectName;

    private String projectRole;

    private String projectLink;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startProjectDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endProjectDate;

    private String projectDescribe;

    private String projectPerformance;

}
