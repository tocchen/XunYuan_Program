package top.tocchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author tocchen
 * @date 2023/2/9 20:58
 * @since jdk 1.8
 **/
@SpringBootApplication
@MapperScan(value = "top.tocchen.mapper")
public class SystemManagerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemManagerUserApplication.class);
    }
}
