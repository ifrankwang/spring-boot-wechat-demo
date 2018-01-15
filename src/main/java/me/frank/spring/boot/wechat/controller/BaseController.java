package me.frank.spring.boot.wechat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.wechat.dto.AppResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "暂无归类接口")
@RestController
public class BaseController {

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试API",
            notes = "返回数据：Hello World!",
            response = String.class)
    @RequestMapping(value = "/test", method = {POST, GET})
    public AppResponse<String> index() {
        return AppResponse.success("Hello World!");
    }
}
