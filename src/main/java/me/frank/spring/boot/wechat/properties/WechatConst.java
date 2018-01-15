package me.frank.spring.boot.wechat.properties;

public class WechatConst {
    public static final String ENCODE = "utf-8";
    public static final String CODED_URL_PREFIX = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    public static final String CODED_URL_REDIRECT_PARAM = "&redirect_uri=";
    public static final String CODED_URL_SUFFIX = "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
}
