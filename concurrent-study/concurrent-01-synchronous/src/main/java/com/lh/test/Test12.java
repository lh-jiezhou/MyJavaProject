package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * interrupt()方法
 *     打断正在运行的线程
 *      只是知道有线程在干扰我，并不会停止运行
 *     要停止运行，要靠打断标记
 *     即被打断(interrupt)线程，由自己决定要不要继续运行
 */
@Slf4j(topic = "c.Test12")
public class Test12 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while (true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted){ // 被执行过interrupt()方法
                    log.debug("被打断了,退出循环");
                    break;
                }
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        log.debug("打断标记: {}", t1.isInterrupted());
        t1.interrupt(); // 线程并不会停下来,但打断标记会变成true
        log.debug("打断标记: {}", t1.isInterrupted());
    }
}
