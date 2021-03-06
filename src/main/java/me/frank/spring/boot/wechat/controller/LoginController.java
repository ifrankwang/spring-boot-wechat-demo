package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.frank.spring.boot.wechat.dto.AppResponse;
import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.service.IJwtService;
import me.frank.spring.boot.wechat.service.ILoginService;
import me.frank.spring.boot.wechat.service.IWechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static me.frank.spring.boot.wechat.properties.SecurityConst.*;

@Api(tags = "登陆相关接口")
@RestController
public class LoginController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final IWechatService wechatService;
    private final ILoginService loginService;
    private final IJwtService jwtService;

    @Autowired
    public LoginController(IWechatService wechatService,
                           ILoginService loginService,
                           IJwtService jwtService) {
        this.wechatService = wechatService;
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @ApiOperation(value = "登录", notes = "登陆即为绑定")
    @PostMapping(LOGIN_URL)
    public AppResponse<Boolean> bind(HttpServletRequest request,
                                     @RequestParam String code) {
        final AppUser USER = (AppUser) request.getAttribute(ATTR_USER);

        // 能到controller层，说明所有校验都通过了
        final WxMpUser WECHAT_USER = wechatService.getUserByCode(code);
        final String OPEN_ID = WECHAT_USER.getOpenId();

        USER.setOpenId(OPEN_ID);
        loginService.saveUserInfo(USER);

        return AppResponse.success(true);
    }

    @ApiOperation(value = "解绑", notes = "解除用户的微信绑定")
    @PostMapping(API_PREFIX + "/unbind")
    public AppResponse<Boolean> unbind(Authentication authentication) {
        final AppUser USER = (AppUser) authentication.getDetails();
        USER.setOpenId(null);
        loginService.saveUserInfo(USER);
        return AppResponse.success(true);
    }

    @ApiOperation(
            value = "更新token",
            notes = "用用户的code换取新token，换取失败，则说明用户未绑定")
    @GetMapping(NO_AUTH_URL + "/refresh-token/{code}")
    public AppResponse<Boolean> refreshToken(@PathVariable String code,
                                             HttpServletResponse response) {
        final WxMpUser WECHAT_USER = wechatService.getUserByCode(code);
        final String OPEN_ID = WECHAT_USER.getOpenId();
        final AppUser USER = loginService.findUserByOpenId(OPEN_ID);
        final String TOKEN = jwtService.genTokenFor(USER.getUsername());

        response.setHeader(HEADER_NAME, TOKEN);
        return AppResponse.success(true);
    }
}
