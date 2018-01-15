package me.frank.spring.boot.wechat.config;

import me.frank.spring.boot.wechat.security.AuthenticationFilter;
import me.frank.spring.boot.wechat.security.AuthorizationFilter;
import me.frank.spring.boot.wechat.util.ServletUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static me.frank.spring.boot.wechat.security.SecurityConst.*;

@EnableWebSecurity
public class WechatSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService service;

    public WechatSecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // security的相关配置
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 在此加入不走权限的API地址
                .antMatchers(TEST_URL, LOGIN_URL, AUTH_FAILED_URL, WECHAT_API).permitAll()
                .anyRequest().permitAll()
                .and()
                // 身份校验过滤器
                .addFilter(new AuthenticationFilter(authenticationManager()))
                // 权限校验过滤器
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 校验异常处理
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 获取用户信息的服务
        auth.userDetailsService(service);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // 解决跨域问题
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        // 校验异常转向处理
        return (request, response, e) -> ServletUtil.forward(request, response, AUTH_FAILED_URL);
    }
}
