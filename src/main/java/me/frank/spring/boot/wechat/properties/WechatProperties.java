package me.frank.spring.boot.wechat.properties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
    private String appId;
    private String appSecret;
    private String token;
    private String aesKey;
    private String pageUrlDomain;
    private String pageUrlPrefix;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getPageUrlDomain() {
        return pageUrlDomain;
    }

    public void setPageUrlDomain(String pageUrlDomain) {
        this.pageUrlDomain = pageUrlDomain;
    }

    public String getPageUrlPrefix() {
        return pageUrlPrefix;
    }

    public void setPageUrlPrefix(String pageUrlPrefix) {
        this.pageUrlPrefix = pageUrlPrefix;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
