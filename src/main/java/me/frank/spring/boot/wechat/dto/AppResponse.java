package me.frank.spring.boot.wechat.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppResponse<T> {
    @ApiModelProperty("成功标志")
    private boolean success;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    private AppResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> AppResponse<T> success(T data) {
        return new AppResponse<>(true, "success", data);
    }

    public static <T> AppResponse<T> failed(String message) {
        return new AppResponse<>(false, message, null);
    }
}
