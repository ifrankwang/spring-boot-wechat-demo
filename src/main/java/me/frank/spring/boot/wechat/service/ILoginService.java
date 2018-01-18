package me.frank.spring.boot.wechat.service;

import me.frank.spring.boot.wechat.entity.AppUser;

public interface ILoginService {
    /**
     * 根据openId获取用户信息
     *
     * @param openId 微信openId
     * @return 用户信息
     */
    AppUser findUserByOpenId(String openId);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     */
    void saveUserInfo(AppUser user);
}
