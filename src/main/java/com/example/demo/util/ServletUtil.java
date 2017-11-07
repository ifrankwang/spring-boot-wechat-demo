package com.example.demo.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {

    /**
     * URL转向，原有请求参数保持不变
     *
     * @param request  请求体
     * @param response 回应体
     * @param url      转向地址
     * @throws ServletException 转向异常
     * @throws IOException      读写异常
     */
    public static void forward(HttpServletRequest request,
                               HttpServletResponse response,
                               String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
