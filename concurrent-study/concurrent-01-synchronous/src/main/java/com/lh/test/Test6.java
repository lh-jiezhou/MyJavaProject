package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * sleep()方法
 * RUNNABLE 变为 TIMED_WAITING 状态
 */
@Slf4j(topic = "c.Test6")
public class Test6 {

    public static void main(String[] args) {

        Thread t1 = new Thread("t1"){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000); // 2秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        log.debug("t1 state: {}", t1.getState());

        try {
            Thread.sleep(1000); // 在哪个线程调用就休眠哪个线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state: {}", t1.getState());

    }

}
