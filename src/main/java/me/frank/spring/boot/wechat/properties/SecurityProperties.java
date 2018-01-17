package me.frank.spring.boot.wechat.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private long expirationTime;
    private String secret;

    public long getExpirationTime() {
        return expirationTime;
    }

    public SecurityProperties setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public SecurityProperties setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
