package top.tocchen.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author tocchen
 * @date 2023/2/10 17:19
 * @since jdk 1.8
 **/
@Configuration
@EnableSwagger2
public class CustomSwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("tocchen")
                .apiInfo(new ApiInfoBuilder()
                        .title("寻猿项目-API文档")
                        .description("本文档描述项目的接口定义")
                        .version("1.0.0-Beta")
                        .contact(new Contact("tocchen","tocchen.top","tocchen@163.com"))
                        .build()
                ).select()
                .paths(Predicates.and(PathSelectors.regex("/.*")))
                .build();
    }
}
