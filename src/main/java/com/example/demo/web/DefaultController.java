package com.example.demo.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller 示例
 */
@RestController
public class DefaultController {

    @ApiOperation(value = "无权限限制测试API", notes = "正常则返回：Hello World!")
    @RequestMapping("/test")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @ApiOperation(value = "权限测试API", notes = "有权限的访问返回登录用户名")
    @PostMapping("/security-test")
    @ResponseBody
    public String test(Authentication authentication) {
        return authentication.getName();
    }
}
