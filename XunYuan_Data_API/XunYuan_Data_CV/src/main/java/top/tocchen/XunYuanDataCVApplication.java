package top.tocchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author tocchen
 * @date 2023/2/20 15:35
 * @since jdk 1.8
 **/
@SpringBootApplication
@MapperScan(basePackages = "top.tocchen.mapper")
public class XunYuanDataCVApplication {
    public static void main(String[] args) {
        SpringApplication.run(XunYuanDataCVApplication.class,args);
    }
}
