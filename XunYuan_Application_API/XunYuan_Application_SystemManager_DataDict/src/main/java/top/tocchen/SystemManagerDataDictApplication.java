package top.tocchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tocchen
 * @date 2023/2/15 17:21
 * @since jdk 1.8
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SystemManagerDataDictApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemManagerDataDictApplication.class,args);
    }
}
