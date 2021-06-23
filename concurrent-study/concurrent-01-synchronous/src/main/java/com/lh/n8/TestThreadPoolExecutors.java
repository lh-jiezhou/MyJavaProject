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
 *      newSingleThreadExecutor
 *
 */
@Slf4j(topic = "c.TestThreadPoolExecutors")
public class TestThreadPoolExecutors {

    public static void main(String[] args) {
        // 固定大小的线程池 (线程池中的线程 非守护线程，不会随着 主线程结束而结束)
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            // 自定义线程名
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
