package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 同步模式-顺序控制
 *      如何控制 先2后1
 *      1、使用 wait(), notify()
 *      2、使用 ReentrantLock的 await(), single()
 *      3、使用 park(), unpark() 【Test26.java】
 */
@Slf4j(topic = "c.Test25")
public class Test25 {

    static final Object lock = new Object();
    static boolean t2runned = false; // 表示t2是否运行过

    public static void main(String[] args) {
        // 如何控制 先2后1
        Thread t1 = new Thread(() -> {

            synchronized (lock){
                while(!t2runned){ // 防止虚假唤醒
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                log.debug("2");
                t2runned = true;
                lock.notifyAll();
            }
        }, "t2");


        t1.start();
        t2.start();

    }
}
