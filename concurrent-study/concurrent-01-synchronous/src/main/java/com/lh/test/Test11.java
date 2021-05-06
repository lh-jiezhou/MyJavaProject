package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * interrupt() 方法
 *     打断阻塞状态下的线程
 *          阻塞 sleep()、wait()、join()
 *
 *     isInterrupted()线程打断标记
 *          如果被打断过 就为 true
 *           但是 sleep,wait,join会重置打断标记为 false
 */
@Slf4j(topic = "c.Test11")
public class Test11 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            log.debug("sleep...");
            try {
                Thread.sleep(5000); // sleep,wait,join会重置打断标记false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标记: {}", t1.isInterrupted());

    }
}
