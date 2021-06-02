package com.lh.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 方式三：使用注解方式实现AOP
 */

@Aspect // 标注这个类是一个切面
public class AnnotationPointCut {


    @Before("execution(* com.lh.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("====方法执行前=====");
    }

    @After("execution(* com.lh.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("====方法执行后=====");
    }


    // 在环绕增强中，可以给定一个参数，代表要获取处理切入的点
    @Around("execution(* com.lh.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
//        // 可以调用joinPoint其他方法
//        Signature signature = joinPoint.getSignature(); // 获得签名
//        System.out.println("=="+signature+"==");

        Object proceed = joinPoint.proceed();  // 执行方法

        System.out.println("环绕后");
    }
    /**
     * output:
     * 环绕前
     * ====方法执行前=====
     * 增加了用户
     * ====方法执行后=====
     * 环绕后
     */
}
