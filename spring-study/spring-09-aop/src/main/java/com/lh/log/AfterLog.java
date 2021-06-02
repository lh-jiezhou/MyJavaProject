package com.lh.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 方式一：实现SpringAPI接口
 */
public class AfterLog implements AfterReturningAdvice {

    /**
     * 后置通知
     * @param returnValue 返回值
     * @param method
     * @param objects
     * @param target
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        // 执行之后
        System.out.println("执行了"+method.getName()+"方法，返回结果为 "+returnValue);
    }
}
