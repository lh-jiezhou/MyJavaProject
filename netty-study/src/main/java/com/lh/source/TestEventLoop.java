package com.lh.source;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * 测试 EventLoop 的 Nio 线程在何时启动
 *
 */
public class TestEventLoop {

    public static void main(String[] args) {

        EventLoop eventLoop = new NioEventLoopGroup().next();

        eventLoop.execute(() -> {
            System.out.println("hello");
        });

    }

}
