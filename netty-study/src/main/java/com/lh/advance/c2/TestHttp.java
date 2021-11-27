package com.lh.advance.c2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

/** 2
 * Http 协议编解码器
 *      Codec : Decode + Encoder  既是入站处理器也是出站处理器
 */
@Slf4j
public class TestHttp {


    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                    // netty 提供的 http 编解码器 (模拟http服务器端) ;; 既是入站处理器也是出站处理器
                    ch.pipeline().addLast(new HttpServerCodec());
                    // 解码后处理
                   /* ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            // 解码后的类型包括  请求头 DefaultHttpRequest; 请求体 LastHttpContent$1
                            log.debug("{}", msg.getClass());
                            if(msg instanceof HttpRequest){ // 请求行, 请求头

                            } else if(msg instanceof HttpContent) { // 请求体

                            }
                        }
                    });*/
                    // 对特定的类型感兴趣（如HttpRequest）; 简写 (入站处理器)
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                            // 获取请求信息
                            log.debug(msg.uri());

                            // 向浏览器(客户端)返回响应 参数(http版本; 状态码)
                            DefaultFullHttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);

                            byte[] bytes = "<h1>Hello,World!</h1>".getBytes();
                            // 设置响应头长度
                            response.headers().setInt(CONTENT_LENGTH, bytes.length);
                            // 响应内容 content() 为 ByteBuf 类型
                            response.content().writeBytes(bytes);

                            // 写回响应
                            ctx.writeAndFlush(response);
                        }
                    });




                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
