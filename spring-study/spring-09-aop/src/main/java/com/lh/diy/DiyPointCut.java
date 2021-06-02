package com.lh.diy;

/**
 * 方式二： 自定义 切入点类
 */
public class DiyPointCut {

    public void before(){
        System.out.println("====方法执行前=====");
    }

    public void after(){
        System.out.println("====方法执行后=====");
    }

}
