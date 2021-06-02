package com.lh.demo03;

public class Client {

    public static void main(String[] args) {

        // 真实角色 (为啥在客户中 新建 真实角色； 都能新建了还要代理做啥？？)
        Host host = new Host();

        // 代理角色
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        // 通过调用程序处理角色来处理我们要调用的接口对象
        handler.setRent(host);

        Rent proxy = (Rent) handler.getProxy();
        proxy.rent();

    }

}
