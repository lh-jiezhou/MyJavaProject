package com.lh.demo02;


// 客户 使用代理
public class Client {

    public static void main(String[] args) {

        // set 真实对象？？？
//        UserServiceImpl userService = new UserServiceImpl();
        UserServiceImpl2 userService2 = new UserServiceImpl2();

        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService2);

        proxy.add();
    }
}
