package com.lh.netty.c4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * handler ( head -> ...  -> tail )
 *      InboundHandler 入站处理器 ( 从前到后处理)
 *      OutboundHandler 出站处理器 ( 从后到前处理)
 *          调用 ch.writeAndFlush() 才会触发出站动作
 *              注意 ch.writeAndFlush()  是从 tail 向前
 *                   ctx.writeAndFlush()   是从当前位置 向前
 *          调用 super.channelRead() 传递控制权 (或者直接调用其实现 ctx.fireChannelRead(msg) )
 *              如果不调, 链会断开
 *
 *       使用 c3 中的 CloseFutureClient 作为客户端进行测试
 *       输出 11 22 33 66 55 44
 *
 *       入站 handler -> super.channelRead() -> 入站 handler -> ch.writeAndFlush() -> 出站 handler
 *
 *
 */
@Slf4j
public class TestPipeline {

    public static void main(String[] args) {

        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加 handler
                        // 1. 通过 channel 拿到 pipeline (流水线对象)
                        ChannelPipeline pipeline = ch.pipeline();
                        // 2. 添加处理器 (流水线中默认有 head -> ...(h1 -> h2 -> h3 -> ) -> tail) 且为双向链表
                        // Inbound
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("11");
                                ByteBuf buf = (ByteBuf) msg;
                                String name = buf.toString(Charset.defaultCharset());
                                // 传递给下一个 handle ; channelRead 将控制器交给下一个入站处理器 handler
                                super.channelRead(ctx, name);
//                                ctx.fireChannelRead(name);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object name) throws Exception {
                                log.debug("22");
                                // 此 name 为上一个 handler 传过来的 name; 转为对象
                                Student student = new Student(name.toString());
                                super.channelRead(ctx, student);
                            }
                        });
                        pipeline.addLast("h3", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("33, 结果{}, class:{}", msg, msg.getClass());
                                // 可以不再调用 (channelRead 将控制器交给下一个入站处理器 )
//                                super.channelRead(ctx, msg);
                                // 实验一: 写 ( 分配一个 ByteBuf 对象; 字节数组 )
//                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));

                                // 实验二: 使用 ctx;  ctx是从当前向前找出站处理器; 而 ch 是从tail向前找
                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                            }
                        });
                        // Outbound (向 channel 写了数据才会触发)
                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("44");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h5", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("55");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h6", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("66");
                                super.write(ctx, msg, promise);
                            }
                        });

                    }
                })
                .bind(8080);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Student {
        private String name;
    }

}
