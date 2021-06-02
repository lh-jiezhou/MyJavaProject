package com.lh.demo04;

import com.lh.demo02.UserService;
import com.lh.demo02.UserServiceImpl;
import com.lh.demo02.UserServiceImpl2;

public class Client {

    public static void main(String[] args) {
        // 真实角色
//        Object userService = new UserServiceImpl();
        Object userService = new UserServiceImpl2();

        // 代理角色
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        // 设置要代理的对象(真实角色)
        handler.setTarget(userService);

        // 动态生成代理类
        UserService proxy = (UserService) handler.getProxy();

        proxy.query();

    }
}
