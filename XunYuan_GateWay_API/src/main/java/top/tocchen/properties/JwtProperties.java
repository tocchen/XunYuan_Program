package top.tocchen.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tocchen
 * @date 2023/2/24 13:00
 * @since jdk 1.8
 **/
@Configuration
@ConfigurationProperties(prefix = "xunyuan.jwt")
@Data
public class JwtProperties {

    private int expiration = 1200;

    private String secretSalt = "secretSalt";

    private String tokenHead = "Bearer";

    private String tokenHeader= "Authorization";

}
