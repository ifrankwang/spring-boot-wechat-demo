package me.frank.spring.boot.wechat.dto;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;

/**
 * 微信菜单按钮类
 */
public class WechatMenuButton extends WxMenuButton {

    public WechatMenuButton(String name) {
        super();
        setName(name);
    }

    public WechatMenuButton(String name, String url) {
        super();
        setName(name);
        setUrl(url);
        setType(WxConsts.BUTTON_VIEW);
    }

    public WechatMenuButton(String name, String key, String type) {
        super();
        setName(name);
        setKey(key);
        setType(type);
    }

    public WechatMenuButton addSubButton(String name, String url) {
        getSubButtons().add(new WechatMenuButton(name, url));
        return this;
    }
}
