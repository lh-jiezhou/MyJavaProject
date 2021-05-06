package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程
 * 执行后 t1、t2 交替运行
 * 单核测试 会导致其他程序运行不了
 */

@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                log.debug("running...");
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                log.debug("running...");
            }
        }, "t2").start();
    }
}
