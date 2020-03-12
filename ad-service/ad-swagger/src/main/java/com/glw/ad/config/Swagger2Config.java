package com.glw.ad.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : glw
 * @date : 2020/3/12
 * @time : 22:31
 * @Description : swagger API文档配置类
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("restful")
                .select()
                .apis(Predicates.and(RequestHandlerSelectors.any()))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("广告系统后台接口 API文档")
                .description("")
                .termsOfServiceUrl("https://www.github.com/DragonV96")
                .contact(new Contact("glw", "", ""))
                .version("1.0.0")
                .build();
    }
}
