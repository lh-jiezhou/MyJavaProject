package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * JDK Executors 类中提供了众多工厂方法来创建各种用途的线程池
 *      newFixedThreadPool: 固定大小的线程池
 *      newCachedThreadPool: 带缓冲的线程池
 *      newSingleThreadExecutor
 *
 */
@Slf4j(topic = "c.TestSynchronousQueue")
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                log.debug("putting {} ", 1);
                integers.put(1);
                log.debug("{} putted...", 1);

                log.debug("putting...{} ", 2);
                integers.put(2);
                log.debug("{} putted...", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        sleep(1);

        new Thread(() -> {
            try {
                log.debug("taking {}", 1);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        sleep(1);

        new Thread(() -> {
            try {
                log.debug("taking {}", 2);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
    }
}
