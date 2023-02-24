package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/20 14:22
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("xunyuan_user_cv")
public class CVEntity implements Serializable {

    private static final long serialVersionUID = 202302201441L;

    @Id
    private String id;

    private UserInfoEntity userInfo;

    private UserAdvantage userAdvantage;

    private List<UserWorkHistory> userWorkHistory;

    private List<UserProjectHistory> userProjectHistory;

    private List<UserEducational> userEducational;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDateTime;

    private int deleted = 0;

}
