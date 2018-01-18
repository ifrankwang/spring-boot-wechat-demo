package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.wechat.dto.AppResponse;
import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.exception.ServiceException;
import me.frank.spring.boot.wechat.service.IWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static me.frank.spring.boot.wechat.properties.SecurityConst.AUTH_FAILED_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "暂无归类接口")
@RestController
public class BaseController {
    private final IWechatService wechatService;

    @Autowired
    public BaseController(IWechatService wechatService) {
        this.wechatService = wechatService;
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试接口",
            notes = "无需权限<br/>" +
                    "返回数据：Hello world!",
            response = String.class)
    @RequestMapping(value = "/no-auth/test", method = {POST, GET})
    public AppResponse<String> noAuthTest() {
        return AppResponse.success("Hello world!");
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试接口",
            notes = "需要权限访问<br/>" +
                    "返回数据：Hello World!",
            response = String.class)
    @RequestMapping(value = "/test", method = {POST, GET})
    public AppResponse<String> authTest(@RequestAttribute AppUser user) {
        return AppResponse.success("Hello " + user.getUsername() + "!");
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "获取微信跳转URL",
            notes = "URL参数应为http://${wechat.page-url-domain}${server.context-path}/index.html#之后的链接",
            response = String.class)
    @GetMapping(value = "/no-auth/get-redirect-url")
    public AppResponse<String> getRedirectUrl(@RequestParam String url) {
        return AppResponse.success(wechatService.assembleRedirectUrl(url));
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "异常接口",
            notes = "不做调用，校验Token异常将转向此接口")
    @PostMapping(value = AUTH_FAILED_URL)
    public AppResponse error(@RequestAttribute ServiceException error) {
        return AppResponse.failed(error.getMessage());
    }
}
