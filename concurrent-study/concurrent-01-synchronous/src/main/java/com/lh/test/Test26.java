package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 同步模式-顺序控制
 *      如何控制 先2后1
 *      park() , unpark() 保持顺序运行
 */
@Slf4j(topic = "c.Test26")
public class Test26 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t2");
        t2.start();
    }
}
