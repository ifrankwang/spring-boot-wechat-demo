package me.frank.spring.boot.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeansUtil implements ApplicationContextAware {
    private final static Logger LOG = LoggerFactory.getLogger(BeansUtil.class);
    private static ApplicationContext context;

    private BeansUtil() {
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static <T> T getBean(Class<T> aClass) {
        LOG.info("\n静态获取bean：{}", aClass.getName());
        return context.getBean(aClass);
    }
}
