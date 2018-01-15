package me.frank.spring.boot.wechat.dto;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AppResponse<T> {
    @ApiModelProperty("错误代码")
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

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
