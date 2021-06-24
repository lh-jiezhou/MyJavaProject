package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * Semaphore信号量
 *      访问共享资源的线程个数限制
 *      新建线程的上限
 */
@Slf4j(topic = "c.TestSemaphore")
public class TestSemaphore {

    public static void main(String[] args) {

        // 1. 创建 semaphore 对象；参数 permits 许可的线程数
        Semaphore semaphore = new Semaphore(3);

        // 2. 10个线程同时运行
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 获得许可
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{
                    log.debug("running...");
                    sleep(1);
                    log.debug("end...");
                } finally {
                    // 释放许可
                    semaphore.release();
                }
            }).start();
        }
    }
}
