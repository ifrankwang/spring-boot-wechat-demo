package me.frank.spring.boot.wechat.config;

import com.google.common.base.Predicate;
import me.frank.spring.boot.wechat.service.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static me.frank.spring.boot.wechat.properties.SecurityConst.HEADER_STRING;

/**
 * Swagger2的配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private final IJwtService jwtService;

    @Autowired
    public Swagger2Config(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    // 创建API监视bean
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
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
        return and(PathSelectors.regex("/.*"),
                   not(PathSelectors.regex("/error")),
                   not(PathSelectors.regex("/application.*")));
    }

    // 设置请求token
    @Bean
    SecurityConfiguration security() {
        final String TOKEN = jwtService.genTokenFor("jack");
        return new SecurityConfiguration(null, null, null, null,
                                         TOKEN, ApiKeyVehicle.HEADER, HEADER_STRING, ",");
    }
}
