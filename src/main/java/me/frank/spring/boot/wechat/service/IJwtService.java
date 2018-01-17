package me.frank.spring.boot.wechat.service;

public interface IJwtService {

    String genTokenFor(String username);

    String getSubjectFrom(String token);
}
