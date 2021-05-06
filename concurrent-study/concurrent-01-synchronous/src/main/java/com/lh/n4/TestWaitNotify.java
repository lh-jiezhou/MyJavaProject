package com.lh.n4;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * notify() 与 notifyAll()
 */
@Slf4j(topic = "c.TestWaitNotify")
public class TestWaitNotify {
    static final Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {

                log.debug("执行...");
                try {
                    obj.wait(); // 让线程在obj上一直等待下去
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其他代码...");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行...");
                try {
//                    obj.wait();
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其他代码...");
            }
        }, "t2").start();

        // 主线程两步后执行
        sleep(0.5);
        log.debug("唤醒 obj 上其他线程");
        synchronized (obj) {
//            obj.notify(); // 唤醒obj上一个线程(随机)
            obj.notifyAll(); // 唤醒obj上所有等待线程
        }

    }
}
