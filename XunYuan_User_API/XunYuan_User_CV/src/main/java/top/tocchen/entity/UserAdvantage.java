package top.tocchen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/20 14:29
 * @since jdk 1.8
 * 个人优势
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdvantage implements Serializable {

    private static final long serialVersionUID = 202302201429L;

    private String info;

}
