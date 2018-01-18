package me.frank.spring.boot.wechat.exception;

public class ServiceException extends RuntimeException {
    public static final ServiceException NOT_VALID_WECHAT_USER = new ServiceException("获取微信用户身份失败！");
    public static final ServiceException INVALID_USER = new ServiceException("该用户不存在！");
    public static final ServiceException INVALID_PASSWORD = new ServiceException("密码错误！");
    public static final ServiceException INVALID_ARGUMENTS = new ServiceException("缺少必要参数！");
    public static final ServiceException INVALID_TOKEN = new ServiceException("无效的请求！");
    public static final ServiceException USER_UNBIND = new ServiceException("用户未绑定微信！");
    public static final ServiceException USER_HAS_BIND = new ServiceException("用户已绑定微信！");
    public static final ServiceException UNSUPPORTED_ENCODING = new ServiceException("不支持的编码方式！");
    public static final ServiceException INSUFFICIENT_PERMISSION = new ServiceException("权限不足！");

    public ServiceException(String message) {
        super(message);
    }
}
