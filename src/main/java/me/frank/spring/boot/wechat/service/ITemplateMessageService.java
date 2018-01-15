package me.frank.spring.boot.wechat.service;

import me.frank.spring.boot.wechat.dto.TemplateMessageData;

public interface ITemplateMessageService {
    /**
     * 向微信用户推送告警信息
     *
     * @param data 模板内容
     */
    void sendTemplateMessage(TemplateMessageData data);
}
