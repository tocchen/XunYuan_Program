import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tocchen
 * @date 2023/2/20 15:51
 * @since jdk 1.8
 **/
@Data
@Accessors(chain = true)
public class YqTable implements Serializable {
    private String jg;
    private Integer yqs;
}