package me.frank.spring.boot.wechat.service;

import me.frank.spring.boot.wechat.entity.AppUser;

public interface ILoginService {
    AppUser findUserByOpenId(String openId);

    void saveUserInfo(AppUser user);
}
