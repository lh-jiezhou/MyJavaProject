package com.lh.demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过 InvocationHandler,Proxy实现动态代理
 *  Proxy 提供了创建动态类和实例的静态方法，他是由这些方法创建的所有动态代理类的父类
 *  InvocationHandler
 */
// 用这个类来自动生成代理类(动态代理)
public class ProxyInvocationHandler implements InvocationHandler {

    // 被代理的接口
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }

    // 生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    // 处理代理实例 并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName()); // 横切点；加入功能-取得方法名
        // 动态代理的本质: 就是使用反射机制实现
        Object result = method.invoke(target, args); // 调用方法
        return result;
    }

    // 横切加入功能
    public void log(String msg){
        System.out.println("【debug】 执行了"+msg+"方法");
    }
}
