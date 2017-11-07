package com.example.demo.security;

import com.example.demo.util.ResponseData;
import com.example.demo.util.ResultState;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.security.SecurityConstants.AUTH_FAILED_URL;
import static com.example.demo.security.SecurityConstants.LOGIN_URL;

@RestController
public class SecurityController {

    @ApiOperation(value = "登录API", notes = "返回登录成功或失败的状态")
    @PostMapping(LOGIN_URL)
    public ResponseData<Boolean> login(@RequestAttribute boolean success) {
        return success ? ResponseData.successReturn(true) :
                ResponseData.errorReturn(ResultState.NOT_VALID_USER);
    }

    @ApiOperation(value = "系统内部调用的API", notes = "验证权限失败的API会跳转到此API")
    @PostMapping(AUTH_FAILED_URL)
    public ResponseData<ResultState> authFailed() {
        return ResponseData.errorReturn(ResultState.ILLEGAL_REQUEST);
    }
}
