package top.tocchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tocchen
 * @date 2023/2/20 14:17
 * @since jdk 1.8
 **/
@SpringBootApplication
@ComponentScan(basePackages = "top.tocchen")
@EnableCaching
public class XunYuanUserCVApplication {
    public static void main(String[] args) {
        SpringApplication.run(XunYuanUserCVApplication.class,args);
    }
}
