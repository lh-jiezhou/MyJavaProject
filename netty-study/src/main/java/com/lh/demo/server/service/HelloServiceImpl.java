package com.lh.demo.server.service;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String msg) {
        // 设置异常状态 测试
        int i = 1 / 0;

        return "你好, " + msg;
    }
}