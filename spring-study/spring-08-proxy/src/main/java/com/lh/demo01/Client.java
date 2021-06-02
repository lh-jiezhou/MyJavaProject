package com.lh.demo01;

/**
 * 客户：
 *   使用代理
 */
public class Client {

    public static void main(String[] args) {
        // 未使用代理
//        Host host = new Host();
//        host.rent();

        // 使用代理（中介）
        // 1、房东要出租房子
//        Host host = new Host();
        // 2、代理，帮房东出租房子，一般会有一些附属操作
//        Proxy proxy = new Proxy(host);
        Proxy proxy = new Proxy();
        // 3、不用面对房东，直接找中介租房
        proxy.rent();
    }
}
