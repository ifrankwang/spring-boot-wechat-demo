package me.frank.spring.boot.wechat.dto;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 微信模板消息类
 */
public class TemplateMessageData {
    // 微信接受对象列表
    private List<String> openIds;
    // 模板数据
    private List<WxMpTemplateData> data;
    // 模板id
    private String templateId;

    public List<String> getOpenIds() {
        return openIds;
    }

    public void setOpenIds(List<String> openIds) {
        this.openIds = openIds;
    }

    public List<WxMpTemplateData> getData() {
        return data;
    }

    public void setData(List<WxMpTemplateData> data) {
        this.data = data;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
