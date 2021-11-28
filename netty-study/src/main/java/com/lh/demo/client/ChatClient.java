package com.lh.demo.client;

import com.lh.demo.message.*;
import com.lh.demo.protocol.MessageCodecSharable;
import com.lh.demo.protocol.ProcotolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;


@Slf4j
public class ChatClient {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_HANDLER = new MessageCodecSharable();

        // 倒计时锁
        CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
        // 初始: 暂未登录
        AtomicBoolean LOGIN = new AtomicBoolean(false);
        //
        AtomicBoolean EXIT = new AtomicBoolean(false);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_HANDLER);

                    // 心跳机制 (IdleStateHandler 用来 判断是不是 读空闲时间过长 或 写空闲时间过长)
                    // 3s 内 如果没有向服务器写数据, 会触发一个 IdleState#WRITER_IDLE 事件
                    ch.pipeline().addLast(new IdleStateHandler(0, 3, 0));
                    // 需要双向 handler 可以处理读写两种事件 (ChannelDuplexHandler 可同时作为 入站和出站处理器)
                    ch.pipeline().addLast(new ChannelDuplexHandler(){
                        // 用于触发特殊事件
                        @Override
                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                            IdleStateEvent event = (IdleStateEvent) evt;
                            if (event.state() == IdleState.WRITER_IDLE) {
                                // 说明触发了写空闲事件
//                                log.debug("3s 没有写数据了, 发送一个心跳包");
                                ctx.writeAndFlush(new PingMessage());
                            }
                        }
                    });


                    // 业务相关 handler
                    ch.pipeline().addLast("client handler", new ChannelInboundHandlerAdapter(){

                        // channelRead 接收服务器返回的消息数据
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.debug("msg: {}", msg);
                            if( msg instanceof LoginResponseMessage){
                                LoginResponseMessage response = (LoginResponseMessage) msg;
                                if(response.isSuccess()){
                                    // 如果登录成功
                                    LOGIN.set(true);
                                }
                                // 计数减一 唤醒 system in 线程
                                WAIT_FOR_LOGIN.countDown();
                            }


                        }

                        // 在连接建立后触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 新建一个线程, 独立接收用户在控制台的输入, 负责向服务器发送各种各样的消息
                            new Thread(() -> {
                                // 用户输入
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("请输入用户名:");
                                String username = scanner.nextLine();
                                System.out.println("请输入密码:");
                                String password = scanner.nextLine();
                                // 构造消息对象
                                LoginRequestMessage message = new LoginRequestMessage(username, password);
                                // 发送消息
                                ctx.writeAndFlush(message);

                                System.out.println("等待后续操作...");

                                try {
                                    // await 阻塞; 直到计数减为0
                                    WAIT_FOR_LOGIN.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                // 如果登录失败
                                if(!LOGIN.get()){
                                    ctx.channel().close();
                                    return;
                                }

                                // 登录成功 进行后续功能
                                while (true) {
                                    System.out.println("==================================");
                                    System.out.println("send [username] [content]");
                                    System.out.println("gsend [group name] [content]");
                                    System.out.println("gcreate [group name] [m1,m2,m3...]");
                                    System.out.println("gmembers [group name]");
                                    System.out.println("gjoin [group name]");
                                    System.out.println("gquit [group name]");
                                    System.out.println("quit");
                                    System.out.println("==================================");

                                    String command = null;
                                    command = scanner.nextLine();

                                    // 命令解析 (假设正确)
                                    String[] s = command.split(" ");
                                    switch (s[0]){
                                        case "send":
                                            ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
                                            break;
                                        case "gsend":
                                            ctx.writeAndFlush(new GroupChatRequestMessage(username, s[1], s[2]));
                                            break;
                                        case "gcreate":
                                            // 解析
                                            Set<String> set = new HashSet<>(Arrays.asList(s[2].split(",")));
                                            // 记得把自己加入到 group
                                            set.add(username);
                                            ctx.writeAndFlush(new GroupCreateRequestMessage(s[1], set));
                                            break;
                                        case "gmembers":
                                            ctx.writeAndFlush(new GroupMembersRequestMessage(s[1]));
                                            break;
                                        case "gjoin":
                                            ctx.writeAndFlush(new GroupJoinRequestMessage(username, s[1]));
                                            break;
                                        case "gquit":
                                            ctx.writeAndFlush(new GroupQuitRequestMessage(username, s[1]));
                                            break;
                                        case "quit":
                                            // 断开客户端连接
                                            ctx.channel().close();
                                            return;
                                    }

                                }

                            }, "system in").start();
                        }

                        // 在连接断开时触发
                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            log.debug("连接已经断开，按任意键退出..");
                            EXIT.set(true);
                        }

                        // 在出现异常时触发
                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            log.debug("连接已经断开，按任意键退出..{}", cause.getMessage());
                            EXIT.set(true);
                        }


                    });

                }
            });
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            //
            channel.closeFuture().sync();

        } catch (Exception e){
            log.error("client error", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
