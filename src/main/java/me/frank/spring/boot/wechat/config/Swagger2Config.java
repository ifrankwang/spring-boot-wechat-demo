package me.frank.spring.boot.wechat.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Lists.newArrayList;
import static me.frank.spring.boot.wechat.properties.SecurityConst.*;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger2的配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private final String API_KEY_NAME = "token";

    // 创建API监视bean
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                .select()
                .paths(paths())
                .apis(RequestHandlerSelectors.basePackage("me.frank.spring.boot.wechat.controller"))
                .build();
    }

    // 获取API的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微信网页开发服务端")
                .description("基于Spring-boot开发的微信网页开发服务端")
                .version("1.0")
                .build();
    }

    // 需要展示或隐藏的API路径
    @SuppressWarnings("all")
    private Predicate<String> paths() {
        return and(regex("/.*"),
                   not(regex("/error")),
                   not(regex("/application.*")));
    }

    private ApiKey apiKey() {
        return new ApiKey(API_KEY_NAME, HEADER_NAME, "header");
    }

    @SuppressWarnings("all")
    private SecurityContext securityContext() {
        final String SUFFIX = "/.*";
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(and(
                        regex(SUFFIX),
                        not(regex(NO_AUTH_URL + SUFFIX)),
                        not(regex(WECHAT_API + SUFFIX)),
                        not(regex(LOGIN_URL))))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference(API_KEY_NAME, authorizationScopes));
    }
}
