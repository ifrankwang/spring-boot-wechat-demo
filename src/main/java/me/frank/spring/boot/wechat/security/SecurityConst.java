package me.frank.spring.boot.wechat.security;

public class SecurityConst {
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static final String SECRET = "TestSecretKey";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/login";
    public static final String AUTH_FAILED_URL = "/failed-auth";
    public static final String NO_AUTH_URL = "/no-auth/**";
    public static final String ERROR_URL = "/error";
    public static final String WECHAT_API = "/wechat/**";

    public static final String ATTR_ERROR = "error";
    public static final String ATTR_USER = "user";
}
