package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 提交任务
 *      execute()
 *      submit() 提交任务 task，用返回值 Future 获得任务执行结果
 *      invokeAny() 提交 tasks 中所有任务，哪个任务先成功执行完毕，返回此任务执行结果，其它任务取消
 *      invokeAll() 提交 tasks 中所有任务
 */
@Slf4j(topic = "c.TestSubmit")
public class TestSubmit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        method3(pool);
    }

    private static void method3(ExecutorService pool) throws InterruptedException, ExecutionException {
        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin 1");
                    Thread.sleep(1000);
                    log.debug("end 1");
                    return "1";
                },
                () -> {
                    log.debug("begin 2");
                    Thread.sleep(500);
                    log.debug("end 2");
                    return "2";
                },
                () -> {
                    log.debug("begin 3");
                    Thread.sleep(2000);
                    log.debug("end 3");
                    return "3";
                }
        ));
        log.debug("{}", result);
    }

    private static void method2(ExecutorService pool) throws InterruptedException {
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach( f ->  {
            try {
                log.debug("{}", f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void method1(ExecutorService pool) throws InterruptedException, ExecutionException {
        // Future 使用了保护暂停模式
        Future<String> future = pool.submit(() -> { // new Callable
            log.debug("running");
            Thread.sleep(1000);
            return "ok";
        });

        log.debug("{}", future.get());
    }
}
