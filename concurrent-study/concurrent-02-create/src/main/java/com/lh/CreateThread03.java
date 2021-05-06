package com.lh;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test")
public class CreateThread03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程对象
        // 方法三: FutureTask 配合 Thread
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running...");
                // 模拟过一段时间后再返回结果
                Thread.sleep(1000); // 1000ms, 1s
                return 100;

                // 此时只是创建好了任务对象 没执行
            }
        });

        // 线程对象
        Thread t = new Thread(task, "t1");
        t.start();

//        task.get(); // 主线程运行到此处时,等待get结果
        log.debug("{}", task.get()); // Slf4j {} 表占位符
        // 多个参数则用多个 {}
    }

}
