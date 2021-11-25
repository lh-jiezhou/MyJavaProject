package com.lh.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * Netty 中 Promise 继承自 netty中的Future
 */
@Slf4j
public class TestNettyPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 准备 EventLoop 对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        // 2. 可以主动创建 Promise 对象 (看成一个结果的容器); Future 只能被动获取
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        // 3. 任意一个线程执行计算, 计算完毕后向 Promise 填充结果
        new Thread(() -> {
            log.debug("开始计算");
            try {
                int i = 1/0;
                Thread.sleep(1000);
                promise.setSuccess(80);
            } catch (Exception e) {
                e.printStackTrace();
                promise.setFailure(e);
            }
//            promise.setSuccess(80); // 结束后 java 进程也结束
        }, "mythread").start();

        // 4. 接受结果
        log.debug("等待结果");
        log.debug("结果是 {}", promise.get());


    }
}
