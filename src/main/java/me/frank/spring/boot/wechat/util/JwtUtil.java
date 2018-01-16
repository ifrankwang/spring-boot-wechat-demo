package me.frank.spring.boot.wechat.util;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static me.frank.spring.boot.wechat.security.SecurityConst.*;

public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    public static String genTokenFor(String username) {
        LOG.info("\n给用户{}创建Token", username);

        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(HS256, SECRET)
                .compact();
    }

    public static String getSubjectFrom(String token) {
        LOG.info("\n从Token中获取用户信息");

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}
