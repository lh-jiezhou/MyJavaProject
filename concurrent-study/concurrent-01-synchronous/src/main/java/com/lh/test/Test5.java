package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程六种状态
 * NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED
 *(附: start() 只能调用一次 (RUNNABLE状态后不能再调用start ))
 */

@Slf4j(topic = "c.Test")
public class Test5 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run(){
                log.debug("running");
            }
        };

        System.out.println(t1.getState()); // 打印状态
        t1.start();
//        t1.start(); // 只能调用一次 否则 IllegalThreadStateException异常
        System.out.println(t1.getState());
    }
}
