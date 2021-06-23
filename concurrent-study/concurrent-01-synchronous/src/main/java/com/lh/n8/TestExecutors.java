package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK Executors 类中提供了众多工厂方法来创建各种用途的线程池
 *      newFixedThreadPool: 固定大小的线程池
 *      newCachedThreadPool: 带缓冲的线程池
 *      newSingleThreadExecutor: 保证始终有一个可用线程
 *
 */
@Slf4j(topic = "c.TestExecutors")
public class TestExecutors {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    public static void test2() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(() -> {
            log.debug("1");
            int i = 1 / 0;
        });

        pool.execute(() -> {
            log.debug("2");
        });

        pool.execute(() -> {
            log.debug("3");
        });
    }

    private static void test1() {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "mypool_t" + t.getAndIncrement());
            }
        });

        pool.execute(() -> {
            log.debug("1");
        });

        pool.execute(() -> {
            log.debug("2");
        });

        pool.execute(() -> {
            log.debug("3");
        });
    }
}
