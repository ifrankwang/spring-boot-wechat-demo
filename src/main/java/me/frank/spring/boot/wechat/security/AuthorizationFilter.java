package me.frank.spring.boot.wechat.security;

import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.exception.ServiceException;
import me.frank.spring.boot.wechat.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.spring.boot.wechat.exception.ServiceException.INVALID_TOKEN;
import static me.frank.spring.boot.wechat.security.SecurityConst.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private UserDetailsService userDetailsService;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        final String URI = request.getRequestURI();

        LOG.info("\n校验用户访问{}权限...", URI);

        final String HEADER = request.getHeader(HEADER_STRING);

        // 没有token时不校验token
        if (StringUtils.isEmpty(HEADER) || !HEADER.startsWith(TOKEN_PREFIX) || URI.contains(ERROR_URL)) {
            if (StringUtils.isEmpty(HEADER) || !HEADER.startsWith(TOKEN_PREFIX)) {
                LOG.warn("\n用户请求不包含Token！");
            }
            if (URI.contains(ERROR_URL)) {
                LOG.warn("\n转向异常接口！");
            }

            chain.doFilter(request, response);
            return;
        }

        // 解密token
        UsernamePasswordAuthenticationToken token = getToken(request);

        // 设置请求权限
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    // 解密token
    private UsernamePasswordAuthenticationToken getToken(HttpServletRequest request)
            throws ServiceException {
        if (null == userDetailsService) {
            LOG.info("\n获取`userDetailsService` bean 对象");
            userDetailsService = WebApplicationContextUtils
                    .getWebApplicationContext(request.getServletContext())
                    .getBean(UserDetailsService.class);
        }

        final String TOKEN = request.getHeader(HEADER_STRING);

        Asserts.notNull(TOKEN, "Token should not be null, please check your code!");
        LOG.info("\n获取请求中的Token：{}", TOKEN);

        final String USER_NAME = JwtUtil.getSubjectFrom(TOKEN);

        if (!StringUtils.isEmpty(USER_NAME)) {
            // 获取用户信息
            LOG.info("Token'{}'有效！属于用户：{}", TOKEN, USER_NAME);

            final AppUser USER = (AppUser) userDetailsService.loadUserByUsername(USER_NAME);
            request.setAttribute(ATTR_USER, USER);
            return new UsernamePasswordAuthenticationToken(USER_NAME, USER.getPassword(), USER.getAuthorities());
        } else {
            LOG.warn("\n{}不是有效的Token！", TOKEN);
            throw INVALID_TOKEN;
        }
    }
}
