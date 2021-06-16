package com.lh.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class HelloClient {

    public static void main(String[] args) throws InterruptedException {

        // 1. 启动类
        new Bootstrap()
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
                .channel()
                // 6. 向服务器发送数据
                .writeAndFlush("hello, world");
    }
}
