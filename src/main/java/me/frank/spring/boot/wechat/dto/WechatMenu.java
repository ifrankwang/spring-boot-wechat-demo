package me.frank.spring.boot.wechat.dto;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuRule;

import java.util.List;

public class WechatMenu {
    private WxMenu menu;
    private WxMenuRule rule;

    public WechatMenu() {
        menu = new WxMenu();
    }

    public WechatMenu(final String TAG) {
        menu = new WxMenu();
        rule = new WxMenuRule();

        rule.setTagId(TAG);
        menu.setMatchRule(rule);
    }

    public WechatMenu addMenuButton(WechatMenuButton button) {
        menu.getButtons().add(button);
        return this;
    }

    public void setMenuButtons(List<WechatMenuButton> buttons) {
        emptyMenuButtons();
        for (WechatMenuButton button : buttons) {
            menu.getButtons().add(button);
        }
    }

    private void emptyMenuButtons() {
        menu.getButtons().clear();
    }

    public WxMenu getMenu() {
        return menu;
    }

    public void setMenu(WxMenu menu) {
        this.menu = menu;
    }

    public WxMenuRule getRule() {
        return rule;
    }

    public void setRule(WxMenuRule rule) {
        this.rule = rule;
    }
}
