package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static me.frank.spring.boot.wechat.properties.SecurityConst.WECHAT_API;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * @author Binary Wang(https://github.com/binarywang)
 * edited by Frank Wang(https://github.com/ifrankwang)
 */
@Api(tags = "微信菜单操作接口")
@RestController
@RequestMapping(WECHAT_API + "/menu")
public class WechatMenuController implements WxMpMenuService {

    private final WxMpService wxService;

    @Autowired
    public WechatMenuController(WxMpService wxService) {
        this.wxService = wxService;
    }

    @ApiOperation(
            produces = TEXT_PLAIN_VALUE,
            value = "自定义菜单创建接口",
            notes = "详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN<br/>" +
                    "如果要创建个性化菜单，请设置matchrule属性<br/>" +
                    "详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN<br/>" +
                    "返回数据：如果是个性化菜单，则返回menuid，否则返回null")
    @Override
    @PostMapping("/create")
    public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(menu);
    }

    @ApiOperation(
            produces = TEXT_PLAIN_VALUE,
            value = "自定义菜单创建接口",
            notes = "详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN<br/>" +
                    "如果要创建个性化菜单，请设置matchrule属性<br/>" +
                    "详情请见：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN<br/>" +
                    "返回数据：如果是个性化菜单，则返回menuid，否则返回null")
    @Override
    @GetMapping("/create/{json}")
    public String menuCreate(@PathVariable String json) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(json);
    }

    @ApiOperation(
            value = "自定义菜单删除接口",
            notes = "详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015&token=&lang=zh_CN")
    @Override
    @GetMapping("/delete")
    public void menuDelete() throws WxErrorException {
        this.wxService.getMenuService().menuDelete();
    }

    @ApiOperation(
            value = "删除个性化菜单接口",
            notes = "详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN")
    @Override
    @GetMapping("/delete/{menuId}")
    public void menuDelete(@PathVariable @ApiParam("个性化菜单的menuid") String menuId)
            throws WxErrorException {
        this.wxService.getMenuService().menuDelete(menuId);
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "自定义菜单查询接口",
            notes = "详情请见： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014&token=&lang=zh_CN"
    )
    @Override
    @GetMapping("/get")
    public WxMpMenu menuGet() throws WxErrorException {
        return this.wxService.getMenuService().menuGet();
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试个性化菜单匹配结果",
            notes = "详情请见: http://mp.weixin.qq.com/wiki/0/c48ccd12b69ae023159b4bfaa7c39c20.html"
    )
    @Override
    @GetMapping("/menuTryMatch/{userid}")
    public WxMenu menuTryMatch(@PathVariable @ApiParam("可以是粉丝的OpenID，也可以是粉丝的微信号") String userid)
            throws WxErrorException {
        return this.wxService.getMenuService().menuTryMatch(userid);
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "获取自定义菜单配置接口",
            notes = "获取自定义菜单配置接口<br/>" +
                    "本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。<br/>" +
                    "请注意：<br/>" +
                    "1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。<br/>" +
                    "2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。<br/>" +
                    "3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。<br/>" +
                    "4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。<br/>" +
                    "5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。<br/>" +
                    " 接口调用请求说明:<br/>" +
                    "http请求方式: GET（请使用https协议）<br/>" +
                    "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN"
    )
    @Override
    @GetMapping("/getSelfMenuInfo")
    public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
        return this.wxService.getMenuService().getSelfMenuInfo();
    }
}
