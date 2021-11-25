package com.lh.netty.c3;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *  EventLoop 执行
 *      普通任务
 *      定时任务
 */
@Slf4j
public class TestEventLoop {

    public static void main(String[] args) {
        // 1. 创建时间循环组
        EventLoopGroup group = new NioEventLoopGroup(2); // io事件, 普通任务, 定时任务
//        EventLoopGroup group = new DefaultEventLoop(); // 普通任务, 定时任务

        // 查看计算机核数
        System.out.println(NettyRuntime.availableProcessors());

        // 2. 获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 3. 执行普通任务 group 有线程池的 相关方法
        group.next().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });

        // 4. 执行定时任务
        group.next().scheduleAtFixedRate(() -> {
            log.debug("ok");
        }, 0, 1, TimeUnit.SECONDS); // 参数： 延迟多少,时间间隔,时间单位

        log.debug("main");


    }
}
