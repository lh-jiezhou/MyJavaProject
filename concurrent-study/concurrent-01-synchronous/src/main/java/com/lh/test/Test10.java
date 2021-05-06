package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * join() 方法
 *  等待线程运行结束
 *  (线程同步典型应用)
 *  com.lh.n3.TestJoin.java
 */
@Slf4j(topic = "c.Test10")
public class Test10 {

    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            sleep(1);
            log.debug("结束");
            r = 10;
        }, "t1");

        t1.start();
        t1.join(); // 主线程在此处暂停, 直到t1运行结束再恢复
//        Thread.sleep(2000);
        log.debug("结果为: {}", r);
        log.debug("结束");
    }
}
