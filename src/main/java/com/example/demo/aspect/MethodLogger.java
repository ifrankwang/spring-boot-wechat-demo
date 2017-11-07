package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切面Logger类，在方法执行前后log方法名和参数
 */
@Aspect
@Component
public class MethodLogger {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 在Controller和Service方法执行前log方法名和参数
     *
     * @param joinPoint 切入点
     */
    @Before("execution(* com.example.demo..*.*Controller.*(..)) || execution(* com.example.demo.service..*.*(..))")
    public void logMethodBefore(JoinPoint joinPoint) {
        log.info(String.format("开始执行方法: %s", getMethodStr(joinPoint)));
    }

    /**
     * 在Controller和Service方法执行后log方法名和参数
     *
     * @param joinPoint 切入点
     */
    @After("execution(* com.example.demo..*.*Controller.*(..)) || execution(* com.example.demo.service..*.*(..))")
    public void logMethodAfter(JoinPoint joinPoint) {
        log.info(String.format("结束执行方法: %s", getMethodStr(joinPoint)));
    }

    /**
     * 获取方法名称和参数
     *
     * @param joinPoint 切入点
     * @return 方法名称和参数的字符串，格式为：包名.方法名(参数1...)
     */
    private String getMethodStr(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();

        // 获取方法名
        builder.append(String.format(
                "%s.%s(",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName()));

        // 获取参数
        for (Object arg : joinPoint.getArgs()) {
            builder.append(arg);
        }

        builder.append(")");

        return builder.toString();
    }
}
