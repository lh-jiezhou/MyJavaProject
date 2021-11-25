package com.lh.netty.c3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class EventLoopClient {

    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    /**
     * ChannelFuture 配合异步方法 用来 处理结果
     * 带有 Future、Promise 的类型都是和异步方法配套使用；用来处理结果
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {

        ChannelFuture channelFuture = new Bootstrap()
                // 2. 添加 EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择客户端的 channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override // 在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加的处理器
                        ch.pipeline().addLast(new StringEncoder()); // hello 转成 ByteBuf
                    }
                })
                // 5. 连接到服务器
                // connect方法 异步非阻塞; main发起调用 真正执行连接的是另外一个线程 nio 线程
                .connect(new InetSocketAddress("localhost", 8080));


        // 2.1 使用 sync 方法同步处理结果 ctrl+shift+/ 多行注释
     /*   // 阻塞当前线程 直到 nio 线程建立连接完毕
        channelFuture.sync();
        // main 无阻塞向下运行 获取channel 此时异步的connect没有运行完成 所以channel未建立连接
        Channel channel = channelFuture.channel();
        log.debug("{}", channel);
        // 向服务端发送数据
        channel.writeAndFlush("hello world");*/


        // 2.2 使用 addListener(回调对象) 方法异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            // nio 连接建立好之后 会调用 operationComplete
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                // 另一个 nio 线程执行下面代码 而非 2.1 中主线程
                Channel channel = channelFuture.channel();
                log.debug("{}", channel);
                // 向服务端发送数据
                channel.writeAndFlush("hello world");
            }
        });

    }

    /**
     * Channel
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException {
        // 1. 启动类
        Channel channel = new Bootstrap()
                // 2. 添加 EventLoop
                .group(new NioEventLoopGroup())
                // 3. 选择客户端的 channel 实现
                .channel(NioSocketChannel.class)
                // 4. 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override // 在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加的处理器
                        ch.pipeline().addLast(new StringEncoder()); // hello 转成 ByteBuf
                    }
                })
                // 5. 连接到服务器
                .connect(new InetSocketAddress("localhost", 8080))
                // 阻塞方法(同步) 直到连接建立
                .sync()
                // 代表连接对象
                .channel();
        System.out.println(channel);
        System.out.println("  "); // 用于设置断点
    }
}
