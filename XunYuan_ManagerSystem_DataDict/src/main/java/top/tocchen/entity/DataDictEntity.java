package top.tocchen.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author tocchen
 * @date 2023/2/13 18:26
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DataDictEntity implements Serializable {

    static final long serialVersionUID = 202302131826L;

    private int id;

    private int parentId;

    private String name;

    private String value;

    private String dictCode;

    private Date createDateTime;

    private Date updateDateTime;

    private boolean child;

}
