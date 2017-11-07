package com.example.demo.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建API监视bean
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    // 获取API的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 测试程序")
                .description("这是一个测试程序")
                .contact("王小明")
                .version("1.0")
                .build();
    }

    // 需要展示或隐藏的API路径
    private Predicate<String> paths() {
        return and(regex("/.*"),
                not("/error"),
                not("/application.*"),
                not("/mobile/echo.*"),
                not("/mobile/session.*"));
    }

    private Predicate<String> regex(String regex) {
        return PathSelectors.regex(regex);
    }

    private Predicate<String> and(Predicate<String>... predicates) {
        return Predicates.and(predicates);
    }

    private Predicate<String> not(String regex) {
        return Predicates.not(regex(regex));
    }
}
