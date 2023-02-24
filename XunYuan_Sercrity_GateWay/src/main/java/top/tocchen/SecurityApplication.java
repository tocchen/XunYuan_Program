package top.tocchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tocchen
 * @date 2023/2/24 14:33
 * @since jdk 1.8
 **/
@SpringBootApplication
@EnableFeignClients
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}
