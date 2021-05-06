package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * interrupt()方法
 *   打断sleep()线程 （唤醒） 并提示异常
 */
@Slf4j(topic = "c.Test7")
public class Test7 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("enter sleep...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up...");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        log.debug("interrupt...");
        t1.interrupt(); // 唤醒会InterruptException异常
    }
}
