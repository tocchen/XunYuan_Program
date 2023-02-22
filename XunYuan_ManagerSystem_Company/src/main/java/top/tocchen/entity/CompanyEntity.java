package top.tocchen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/11 12:35
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonFormat
public class CompanyEntity implements Serializable {

    static final long serialVersionUID = 202302111238L;

    private String id;

    private String companyName;

    private String companyCode;

    private String apiUrl;

    private String signKey;

    private String contactsName;

    private String contactsPhone;

    private Integer status;

    private Date createDatetime;

    private Date updateDatetime;

    private int star;

}
