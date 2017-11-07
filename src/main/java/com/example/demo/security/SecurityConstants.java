package com.example.demo.security;

public class SecurityConstants {
    public static final String SECRET = "TestSecretKey";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/login";
    public static final String AUTH_FAILED_URL = "/failed-auth";
    public static final String TEST_URL = "/test";
}
