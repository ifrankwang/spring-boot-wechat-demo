package com.example.demo.util;

public enum ResultState {
    SUCCESS(0, "success"),
    NOT_VALID_USER(1, "账号密码错误！"),
    ILLEGAL_REQUEST(2, "非法请求！"),
    NO_PERMISSION(3, "无权限的用户！");
    private int code;
    private String message;

    ResultState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultState{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
