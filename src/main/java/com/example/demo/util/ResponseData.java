package com.example.demo.util;

import io.swagger.annotations.ApiModelProperty;

public class ResponseData<T> {
    @ApiModelProperty("错误代码")
    private int code;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    public ResponseData() {
    }

    public static <T> ResponseData<T> successReturn(T data) {
        return new ResponseData<T>()
                .setState(ResultState.SUCCESS)
                .setData(data);
    }

    public static <T> ResponseData<T> errorReturn(ResultState state) {
        return new ResponseData<T>()
                .setState(state);
    }

    public static <T> ResponseData<T> errorReturn(String message) {
        return new ResponseData<T>()
                .setCode(-1)
                .setMessage(message);
    }

    public ResponseData<T> setState(ResultState state) {
        this.code = state.getCode();
        this.message = state.getMessage();
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResponseData<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
