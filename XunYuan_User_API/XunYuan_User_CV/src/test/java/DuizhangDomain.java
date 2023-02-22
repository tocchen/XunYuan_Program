import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author tocchen
 * @date 2023/2/20 15:50
 * @since jdk 1.8
 **/
@Data
@Accessors(chain = true)
public class DuizhangDomain {
    private String jg;
    private Integer ydz;
    private Integer wdz;
    private BigDecimal dzl;
}