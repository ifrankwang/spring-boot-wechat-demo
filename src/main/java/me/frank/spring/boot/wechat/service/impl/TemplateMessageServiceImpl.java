package me.frank.spring.boot.wechat.service.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.frank.spring.boot.wechat.dto.TemplateMessageData;
import me.frank.spring.boot.wechat.service.ITemplateMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TemplateMessageServiceImpl implements ITemplateMessageService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final WxMpService wechatService;

    public TemplateMessageServiceImpl(WxMpService wechatService) {
        this.wechatService = wechatService;
    }

    @Override
    public void sendTemplateMessage(TemplateMessageData data) {
        if (null != data &&
            !CollectionUtils.isEmpty(data.getData()) &&
            !CollectionUtils.isEmpty(data.getOpenIds())) {
            // 在有数据的情况下组装模板
            data.getOpenIds().forEach(openId -> {
                WxMpTemplateMessage message = new WxMpTemplateMessage();

                message.setToUser(openId);
                message.setTemplateId(data.getTemplateId());

                // 轮询塞入数据对象
                data.getData().forEach(dataObj -> message.getData().add(dataObj));

                try {
                    wechatService.getTemplateMsgService().sendTemplateMsg(message);
                } catch (WxErrorException e) {
                    LOG.error("\n尝试发送模板消息失败！", e);
                }
            });
        }
    }
}
