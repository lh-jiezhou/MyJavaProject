package com.lh;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test")
public class CreateThread01 {

    // 创建线程对象
    // 方法一: 直接使用Thread
    public static void main(String[] args) {
        Thread t = new Thread(){ // 匿名内部类
            @Override
            public void run(){ // 覆写run方法
                // 要执行的任务
                log.debug("running");
            }
        };
        t.setName("t1"); // 设置线程名字
        t.start(); // 启动线程

        log.debug("main-running"); // 主线程中
    }

}
