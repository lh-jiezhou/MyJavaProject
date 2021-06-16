package com.lh.netty.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty 写一个 hello world!
 *      客户端发数据给服务端
 *
 */
public class HelloServer {

    public static void main(String[] args) {

        // 1. 服务端启动器, 负责组装 netty 组件, 启动服务器
        new ServerBootstrap()
                // 2. NioEventLoopGroup 包含 BossEventLoop, WorkerEventLoop(selector, thread); group 组
                .group(new NioEventLoopGroup()) // 先监听 accept 事件 ; 后边 read 事件
                // 3. 选择服务器的实现 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class) // OIO BIO
                // 4. boss 负责处理连接; worker(child) 负责处理读写; childHandler 决定了 worker(child) 能执行哪些操作
                .childHandler( // 添加处理器
                    // 5. channel 代表和客户端进行数据读写的通道 Initializer 初始化；负责添加别的 handler
                    new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 6. 添加具体的 handler
                        ch.pipeline().addLast(new StringDecoder()); // 将 ByteBuf 转换为 字符串
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义 handler
                            @Override // 读事件
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 打印上一步转换后的字符串
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 7. 绑定监听端口
                .bind(8080);
    }
}
