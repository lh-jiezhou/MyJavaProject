package com.lh.source;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 测试 设置 TCP 全连接队列大小
 *      Nio 中可以通过 bind() 传 backlog 参数
 *      Netty 中无法通过 bind(); 需要使用配置
 *
 *      NioEventLoop 在 unsafe.read(); 前设置断点
 *          使 Netty 不直接执行 accept(); 从而 连接进入队列; 测试 连接超队列 时报错
 *
 *      黑马 netty 126 集; 找 变量初始值
 *
 *      下载源码
 *          mvn dependency:resolve -Dclassifier=sources
 *
 */
public class TestBacklogServer {

    public static void main(String[] args) {

        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                // 传递参数 (测试全连接满)
                .option(ChannelOption.SO_BACKLOG, 2)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler());
                    }
                }).bind(8080);
    }

}
