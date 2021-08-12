package com.lh.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * EventLoop 执行
 *      1. io 操作
 *      2. 细分 NioEventLoopGroup 分出 boss 和 worker
 *      3. 进一步细分 EventLoopGroup  新建一个 专门处理 ChannelHandler 时间较长的操作的 group
 *             验证 handler1 与 handler2 分别那个线程运行
 *
 */
@Slf4j
public class EventLoopServer {

    public static void main(String[] args) {

        // 细分2：创建一个独立的 EventLoopGroup 用于专门处理 ChannelHandler 中时间较长的操作
        // 普通任务，定时任务
        EventLoopGroup group = new DefaultEventLoopGroup();

        new ServerBootstrap()
                // boss 和 worker 进一步细分
                // 细分1：第一个参数 boss 只负责 serverSocketChannel 上 accept 事件,第一个参数 worker 只负责 socketChannel 上的读写
                // worker 线程设置为两个
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handle1", new ChannelInboundHandlerAdapter(){
                            @Override   // ByteBuf
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(Charset.defaultCharset())); // 建议 客户端 与 服务器 强制指定 一致的字符编码(而不是默认)
                                ctx.fireChannelRead(msg); // 让消息传递给下一个 handler
                            }
                        }).addLast(group, "handle2", new ChannelInboundHandlerAdapter(){
                            @Override   // ByteBuf
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(Charset.defaultCharset()));
                            }
                        });

                    }
                })
                .bind(8080);
    }
}
