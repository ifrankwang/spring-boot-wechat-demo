package me.frank.spring.boot.wechat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    // 有效时长
    private long expirationTime;
    // 加密秘钥
    private String secret;
}
