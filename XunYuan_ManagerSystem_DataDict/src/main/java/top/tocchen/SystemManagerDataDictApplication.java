package top.tocchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author tocchen
 * @date 2023/2/13 18:19
 * @since jdk 1.8
 **/
@SpringBootApplication
@MapperScan(basePackages = "top.tocchen.mapper")
@EnableCaching
public class SystemManagerDataDictApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemManagerDataDictApplication.class);
    }
}
