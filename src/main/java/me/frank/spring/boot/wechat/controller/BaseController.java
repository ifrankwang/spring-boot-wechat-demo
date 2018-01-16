package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.wechat.dto.AppResponse;
import me.frank.spring.boot.wechat.entity.AppUser;
import me.frank.spring.boot.wechat.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.frank.spring.boot.wechat.security.SecurityConst.ERROR_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "暂无归类接口")
@RestController
public class BaseController {

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
            value = "异常接口",
            notes = "不做调用，校验Token异常将转向此接口")
    @PostMapping(value = ERROR_URL)
    public AppResponse error(@RequestAttribute ServiceException error) {
        return AppResponse.failed(error.getMessage());
    }
}
