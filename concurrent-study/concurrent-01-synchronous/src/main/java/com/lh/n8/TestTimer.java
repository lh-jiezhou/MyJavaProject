package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 任务调度线程池 Scheduled
 *      延时执行任务
 *          Timer ；所有任务都是串行执行的，同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务
 *          ScheduledExecutorService; 并行；且不受异常影响
 *              schedule()
 *      定时执行任务
 *          ScheduledExecutorService
 *              scheduleAtFixedRate()
 *
 * 一个插件
 *      Alibaba Java Coding Guidelines 阿里编码规约
 */
@Slf4j(topic = "c.TestTimer")
public class TestTimer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
////        method1();
//        method2(pool);

        // 主动处理异常
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            log.debug("task1");
            int i = 1 / 0;
            return true;
        });
        // Callable 配合 Future; f.get()返回return值 或 异常信息
        log.debug("result: {}", f.get());

    }

    /**
     *  定时执行; 固定速率；
     *      scheduleWithFixedDelay
     */
    private static void method4() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start...");
        // 参数1：任务对象; 参数2：初始延时; 参数3：执行间隔; 参数4：时间单位
        pool.scheduleWithFixedDelay(() -> {
            log.debug("running...");
            sleep(2);
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     *  定时执行; 固定速率；
     *      scheduleAtFixedRate (任务时长超过执行间隔，则以任务时长为准)
     */
    private static void method3() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        log.debug("start...");
        // 参数1：任务对象; 参数2：初始延时; 参数3：执行间隔; 参数4：时间单位
        pool.scheduleAtFixedRate(() -> {
            log.debug("running...");
            sleep(2);
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 使用 ScheduledExecutorService
     *      出现异常的情况 (不受影响)
     * @param pool
     */
    private static void method2(ScheduledExecutorService pool) {
        log.debug("start...");
        // schedule 参数1：任务; 参数2：延迟时间; 参数3：时间单位
        pool.schedule(() -> {
            log.debug("task1");
            // 出现异常，但未显示
//            int i = 1 / 0;

            // 手动捕捉
            try {
                int i = 1/0;
            } catch (Exception e) {
                log.error("error:", e);
            }

        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
    }


    /**
     * Timer 所有任务都是串行执行的
     */
    private static void method1() {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 1");
                sleep(2);
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };

        log.debug("start...");
        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
    }

}
