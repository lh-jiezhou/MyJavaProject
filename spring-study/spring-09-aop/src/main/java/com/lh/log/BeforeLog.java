package com.lh.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 方式一：实现SpringAPI接口
 */
public class BeforeLog implements MethodBeforeAdvice {

    /**
     * 前置通知
     * @param method 要执行的目标对象的方法
     * @param objects 参数
     * @param target 目标对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        // 前面执行
        System.out.println(target.getClass().getName()+"的 "+method.getName()+" 方法被执行了");
    }
}
