package top.tocchen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/23 22:04
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NormalUserVo implements Serializable {

    private static final long serialVersionUID = -4353966955070676867L;

    private String userAccount;

    private String userPassword;

    private String userPhone;

    private String userEmail;

    private String userName;


}
