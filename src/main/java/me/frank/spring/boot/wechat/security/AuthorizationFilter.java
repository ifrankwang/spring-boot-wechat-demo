package me.frank.spring.boot.wechat.security;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static me.frank.spring.boot.wechat.security.SecurityConst.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        // 没有token时不校验token
        if (StringUtils.isEmpty(header) || !header.startsWith(TOKEN_PREFIX)) {
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
    private UsernamePasswordAuthenticationToken getToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (!StringUtils.isEmpty(token)) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (!StringUtils.isEmpty(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
            return null;
        }
        return null;
    }
}
