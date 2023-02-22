package top.tocchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author tocchen
 * @date 2023/2/19 23:22
 * @since jdk 1.8
 **/
@SpringBootApplication
@EnableCaching
public class CompanyWorkListApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyWorkListApplication.class,args);
    }
}
