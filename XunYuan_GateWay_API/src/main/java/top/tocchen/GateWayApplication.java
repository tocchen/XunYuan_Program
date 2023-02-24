package top.tocchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tocchen
 * @date 2023/2/22 18:41
 * @since jdk 1.8
 **/
@SpringBootApplication
//@EnableFeignClients
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}
