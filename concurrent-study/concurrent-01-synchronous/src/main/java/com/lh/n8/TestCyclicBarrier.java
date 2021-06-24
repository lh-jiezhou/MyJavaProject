package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * CyclicBarrier 循环珊栏
 *      由于CountDownLatch无法被重用
 *      CyclicBarrier可重用;
 *          线程数和任务数最好保持一致
 */
@Slf4j(topic = "c.TestCyclicBarrier")
public class TestCyclicBarrier {

    public static void main(String[] args) {
        // 线程数和任务数最好保持一致 parties == nThreads
        ExecutorService service = Executors.newFixedThreadPool(2);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            // CyclicBarrier 减到0 后运行
            log.debug("task1 task2 finish...");
        });

        for (int i = 0; i < 3; i++) {
            service.submit(() -> {
                log.debug("task1 start...");
                sleep(1);
                try {
                    // 等待
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            service.submit(() -> {
                log.debug("task2 start...");
                sleep(2);
                try {
                    // 等待
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        service.shutdown();





    }

    /**
     * CountDownLatch无法被重用
     */
    private static void test1() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        // 执行3遍
        for (int i = 0; i < 3; i++) {
            // 被创建3次 无法重用
            CountDownLatch latch = new CountDownLatch(2);
            service.submit(() -> {
                log.debug("task1 start...");
                sleep(1);
                latch.countDown();
            });
            service.submit(() -> {
                log.debug("task2 start...");
                sleep(2);
                latch.countDown();
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task1 task2 finish...");
        }
        // 停止线程池
        service.shutdown();
    }
}
