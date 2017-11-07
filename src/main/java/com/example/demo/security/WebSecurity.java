package com.example.demo.security;

import com.example.demo.util.ServletUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.example.demo.security.SecurityConstants.*;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsService service;

    public WebSecurity(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // security的相关配置
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 在此加入不走权限的API地址
                .antMatchers(TEST_URL, LOGIN_URL, AUTH_FAILED_URL).permitAll()
                .anyRequest().authenticated()
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
    CorsConfigurationSource corsConfigurationSource() {
        // 跨域问题的相关处理
        UrlBasedCorsConfigurationSource config = new UrlBasedCorsConfigurationSource();
        config.registerCorsConfiguration("/**",
                new CorsConfiguration().applyPermitDefaultValues());
        return config;
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        // 校验异常转向处理
        return (request, response, e) -> ServletUtil.forward(request, response, AUTH_FAILED_URL);
    }
}
