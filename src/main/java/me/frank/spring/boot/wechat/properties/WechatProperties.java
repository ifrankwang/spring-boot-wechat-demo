package me.frank.spring.boot.wechat.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
    private String appId;
    private String appSecret;
    private String token;
    private String aesKey;
    // 前端网站域名
    private String pageUrlDomain;
    // 前端网站前缀
    private String pageUrlPrefix;
    // 隐含的code和对应的openId
    private Map<String, String> implicitCode;
}
