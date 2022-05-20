package com.itran.fgoc.common.swagger.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;


/**
 * @author cocoyang
 * @version 1.0
 **/
@Configuration("FgocSwaggerConfig")
@EnableSwagger2
public class FgocSwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * swagger 文档扫描的包
     */
    private static final String BASE_PACKAGE = "com.itran.fgoc";

    /**
     * 创建获取api应用
     * @return
     */
    @Bean
    public Docket createFgocRestApi() {
        HashSet<String> strings = new HashSet<>();
        strings.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(applicationName)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(applicationName+"-api文档")
                        .description(applicationName+"-api文档")
                        .version("1.0")
                        .build()).produces(strings);
    }


}


