package me.frank.spring.boot.wechat.dto;

import lombok.Data;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

import java.util.List;

/**
 * 微信模板消息类
 */
@Data
public class TemplateMessageData {
    // 微信接受对象列表
    private List<String> openIds;
    // 模板数据
    private List<WxMpTemplateData> data;
    // 模板id
    private String templateId;
}
