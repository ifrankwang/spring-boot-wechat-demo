package me.frank.spring.boot.wechat.properties;

public class SecurityConst {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/login";
    public static final String NO_AUTH_URL = "/no-auth/**";
    public static final String ERROR_URL = "/error";
    public static final String WECHAT_API = "/wechat/**";

    public static final String ATTR_ERROR = "error";
    public static final String ATTR_USER = "user";
}
