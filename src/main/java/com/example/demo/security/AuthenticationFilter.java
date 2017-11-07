package com.example.demo.security;

import com.example.demo.model.AppUser;
import com.example.demo.util.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static com.example.demo.security.SecurityConstants.*;

// 登录时会调用的过滤器
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            // 获取登录参数
            AppUser user = new ObjectMapper()
                    .readValue(request.getInputStream(), AppUser.class);
            // 校验用户名密码
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.emptyList()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // 登录校验成功后的操作
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        // 生成token
        String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
        // 加入回应头中
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        // 校验成功后重定向到登陆API中，返回相应数据
        goToLogin(request, response, true);
    }

    // 登陆校验失败后的操作
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 校验失败后重定向到登陆API中，返回相应数据
        goToLogin(request, response, false);
    }

    // 重定向
    private void goToLogin(HttpServletRequest request, HttpServletResponse response, boolean success)
            throws ServletException, IOException {
        request.setAttribute("success", success);
        ServletUtil.forward(request, response, LOGIN_URL);
    }
}
