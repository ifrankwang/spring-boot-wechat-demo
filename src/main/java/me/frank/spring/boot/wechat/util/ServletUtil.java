package me.frank.spring.boot.wechat.util;

import me.frank.spring.boot.wechat.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.spring.boot.wechat.properties.SecurityConst.ATTR_ERROR;
import static me.frank.spring.boot.wechat.properties.SecurityConst.AUTH_FAILED_URL;

public class ServletUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ServletUtil.class);

    /**
     * URL转向
     *
     * @param request  请求
     * @param response 回应
     * @param url      转向地址
     */
    public static void forward(HttpServletRequest request,
                               HttpServletResponse response,
                               String url) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            LOG.error("\n页面转向异常！", e);
        }
    }

    /**
     * 转向异常链接（返回异常信息给用户）
     * @param request 请求
     * @param response 回应
     * @param exception 异常信息
     */
    public static void goError(HttpServletRequest request,
                               HttpServletResponse response,
                               ServiceException exception) {
        request.setAttribute(ATTR_ERROR, exception);
        forward(request, response, AUTH_FAILED_URL);
    }
}
