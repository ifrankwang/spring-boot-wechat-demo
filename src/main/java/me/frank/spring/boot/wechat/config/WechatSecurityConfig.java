package me.frank.spring.boot.wechat.config;

import me.frank.spring.boot.wechat.properties.SecurityProperties;
import me.frank.spring.boot.wechat.security.AuthenticationFilter;
import me.frank.spring.boot.wechat.security.AuthorizationFilter;
import me.frank.spring.boot.wechat.service.IJwtService;
import me.frank.spring.boot.wechat.util.ServletUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

import static me.frank.spring.boot.wechat.exception.ServiceException.INSUFFICIENT_PERMISSION;
import static me.frank.spring.boot.wechat.properties.SecurityConst.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class WechatSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    public WechatSecurityConfig(UserDetailsService userDetailsService,
                                IJwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String REGEX_FIX = "/**";
        // security的相关配置
        http.cors().and().csrf().disable()
            // 身份校验过滤器
            .addFilter(new AuthenticationFilter(authenticationManager(), userDetailsService, jwtService))
            // 权限校验过滤器
            .addFilter(new AuthorizationFilter(authenticationManager(), userDetailsService, jwtService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 在此加入不走权限的API地址
            .antMatchers(REGEX_FIX + NO_AUTH_URL + REGEX_FIX,
                         LOGIN_URL, AUTH_FAILED_URL)
            .permitAll()
            .anyRequest().authenticated()
            .and()
            // 校验异常处理
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态网页资源不做安全校验
        web.ignoring()
           .antMatchers("/resources/**", "/static/**", "/public/**",
                        "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**",
                        "/webjars/**", "/*.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 获取用户信息的服务
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        // 校验异常转向处理
        return (request, response, e) -> ServletUtil.goError(request, response, INSUFFICIENT_PERMISSION);
    }
}
