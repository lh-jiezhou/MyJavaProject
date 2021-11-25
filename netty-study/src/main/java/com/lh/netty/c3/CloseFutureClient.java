package com.lh.netty.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * channelFuture 关闭问题
 *      场景：客户端不断接受用户的输入，将输入发送给服务器端；输入 Q 表示退出
 *
 */
@Slf4j
public class CloseFutureClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 调整日志级别，用于测试
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));

        // sync()同步等待，等channel真正建立好; 再通过 channel() 拿到channel对象
        Channel channel = channelFuture.sync().channel();
        log.debug("{}", channel);
        // 启动一个新的线程，处理用户控制台的输入
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            // 持续输入
            while (true) {
                String line = scanner.nextLine();
                // 退出
                if("q".equals(line)){
                    channel.close(); // close() 是异步操作, 交给其他线程真正关闭; 可能1s后才真正关闭
//                    log.debug("处理关闭之后的操作"); // 不能在此处处理后续操作
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();

        // 获取 ClosedFuture 对象, 1) 同步处理关闭, 2) 异步处理关闭
        ChannelFuture closeFuture = channel.closeFuture();
        /*//  1) 同步处理关闭
        log.debug("waiting close...");
        closeFuture.sync(); // 同步等待 close() 方法执行完毕
        log.debug("处理关闭之后的操作");*/
        // 2) 异步处理关闭, 回调
        closeFuture.addListener((ChannelFutureListener) future -> {
            log.debug("处理关闭之后的操作");
            // 关闭线程 (优雅)
            group.shutdownGracefully();
        });

        // 关闭后 client java 进程还未结束, 原因: group(new NioEventLoopGroup()) 中还有线程




    }

}
