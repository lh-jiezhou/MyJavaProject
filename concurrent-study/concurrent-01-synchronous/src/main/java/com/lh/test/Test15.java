package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * Java进程需要等待所有线程运行结束，才会结束
 *      在log处设断点实验
 *      在if处添加断点
 *
 *  但只要其它非守护线程运行结束了
 *      只剩下守护线程Daemon  会强制结束
 *
 */
@Slf4j(topic = "c.Test15")
public class Test15 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted())
                    break;
            }
            log.debug("结束");
        }, "t1");

        t1.setDaemon(true); // 在start之前设置守护线程
        t1.start();

        Thread.sleep(1000);
        log.debug("结束");
    }

}
