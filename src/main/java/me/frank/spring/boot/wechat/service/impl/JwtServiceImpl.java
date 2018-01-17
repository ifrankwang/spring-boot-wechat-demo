package me.frank.spring.boot.wechat.service.impl;

import io.jsonwebtoken.Jwts;
import me.frank.spring.boot.wechat.properties.SecurityProperties;
import me.frank.spring.boot.wechat.service.IJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static me.frank.spring.boot.wechat.properties.SecurityConst.TOKEN_PREFIX;

@Service
public class JwtServiceImpl implements IJwtService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final SecurityProperties properties;

    public JwtServiceImpl(SecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public String genTokenFor(String username) {
        LOG.info("\n给用户{}创建Token", username);

        final long EXPIRATION_TIME = properties.getExpirationTime();
        final String SECRET = properties.getSecret();

        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(HS256, SECRET)
                .compact();
    }

    @Override
    public String getSubjectFrom(String token) {
        LOG.info("\n从Token中获取用户信息");
        final String SECRET = properties.getSecret();

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }
}
