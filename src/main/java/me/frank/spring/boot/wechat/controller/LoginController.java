package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.wechat.dto.AppResponse;
import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static me.frank.spring.boot.wechat.security.SecurityConst.HEADER_STRING;
import static me.frank.spring.boot.wechat.security.SecurityConst.LOGIN_URL;

@Api(tags = "登陆相关接口")
@RestController
public class LoginController {

    @ApiOperation(value = "登录", notes = "返回登录成功或失败的状态")
    @PostMapping(LOGIN_URL)
    public AppResponse<Boolean> login() {
        // 能到controller层，说明所有校验都通过了
        return AppResponse.success(true);
    }

    @ApiOperation(value = "更新token", notes = "更新token以免token过期")
    @PostMapping("/refresh-token")
    public AppResponse<Boolean> refreshToken(@RequestAttribute AppUser user,
                                             HttpServletResponse response) {
        final String TOKEN = JwtUtil.genTokenFor(user.getUsername());
        response.setHeader(HEADER_STRING, TOKEN);
        return AppResponse.success(true);
    }
}
