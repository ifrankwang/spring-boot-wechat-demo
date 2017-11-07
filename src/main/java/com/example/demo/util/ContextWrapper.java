package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取ApplicationContext的类
 */
public class ContextWrapper implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextWrapper.context = applicationContext;
    }
}
