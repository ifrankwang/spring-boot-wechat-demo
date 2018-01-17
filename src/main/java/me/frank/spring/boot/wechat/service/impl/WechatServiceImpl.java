package me.frank.spring.boot.wechat.service.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.frank.spring.boot.wechat.exception.ServiceException;
import me.frank.spring.boot.wechat.properties.WechatProperties;
import me.frank.spring.boot.wechat.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static me.frank.spring.boot.wechat.exception.ServiceException.UNSUPPORTED_ENCODING;
import static me.frank.spring.boot.wechat.properties.WechatConst.*;

@Service
public class WechatServiceImpl implements IWechatService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final WxMpService wechatService;
    private final WechatProperties properties;

    public WechatServiceImpl(WxMpService wechatService,
                             WechatProperties properties) {
        this.wechatService = wechatService;
        this.properties = properties;
    }

    /**
     * 根据前端传来的code获取微信用户对象
     *
     * @param code 微信前端传来的code
     * @return 微信用户对象
     */
    @Override
    public WxMpUser getUserByCode(String code) throws ServiceException {
        try {
            WxMpOAuth2AccessToken token = wechatService.oauth2getAccessToken(code);
            WxMpUser wechatUser = wechatService.oauth2getUserInfo(token, null);

            // 刷新微信token
            wechatService.oauth2refreshAccessToken(token.getRefreshToken());

            LOG.debug("\n根据code'{}'换取到的微信用户对象: {}", code, wechatUser);

            return wechatUser;
        } catch (WxErrorException e) {
            // 获取不到对象
            ServiceException exception = ServiceException.NOT_VALID_WECHAT_USER;
            LOG.error("\n" + exception.getMessage(), e);
            throw exception;
        }
    }

    /**
     * 获得微信可获取到code的url
     *
     * @param url 原url
     * @return 可获取到code的url
     */
    @Override
    public String assembleRedirectUrl(String url) throws ServiceException {
        final String CODED_URL;

        try {
            CODED_URL = CODED_URL_PREFIX +
                        properties.getAppId() +
                        CODED_URL_REDIRECT_PARAM +
                        URLEncoder.encode(properties.getPageUrlPrefix() + url, ENCODE) +
                        CODED_URL_SUFFIX;
        } catch (UnsupportedEncodingException e) {
            throw UNSUPPORTED_ENCODING;
        }

        LOG.debug("\n组装微信转向url：" + CODED_URL);
        return CODED_URL;
    }
}
