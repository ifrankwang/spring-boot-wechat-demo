package me.frank.spring.boot.wechat.service;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.frank.spring.boot.wechat.exception.ServiceException;

public interface IWechatService {

    WxMpUser getUserByCode(String code) throws ServiceException;

    String assembleRedirectUrl(String url) throws ServiceException;
}
