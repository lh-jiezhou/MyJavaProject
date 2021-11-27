package com.lh.advance.c1;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *  Netty 中黏包 半包
 *      方法一：客户端使用短连接 避免 黏包
 *             但短连接无法避免半包
 *      方法二：定长消息解码器 服务端 使用 handler; FixedLengthFrameDecoder()
 *              可以解决黏包 半包
 *      方法三：使用分隔符
 *              LineBasedFrameDecoder 换行符作为分割 \n  \r\n
 *              DelimiterBasedFrameDecoder 可自定义分隔符 (ByteBuf类型)
 *       方法四：LTC解码器
 *              参数：// 最大长度，长度偏移，长度占用字节，长度调整，剥离字节数
 *              LengthFieldBasedFrameDecoder
 *              实验见 advance.c1.TestLengthFieldDecoder
 *
 *
 */
public class HelloWorldClient {

    static final Logger log = LoggerFactory.getLogger(HelloWorldClient.class);

    public static void main(String[] args) {
        // 发 10 次 测试是否黏包
        for (int i = 0; i < 10; i++) {
            send();
        }
        System.out.println("finish");

    }

    private static void send() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 处理器
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                        // 会在连接 channel 建立成功后，会触发 active 事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf buf = ctx.alloc().buffer(16);
                            buf.writeBytes(new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17});
                            ctx.writeAndFlush(buf);
                            // 关闭连接
                            ctx.channel().close();
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error("client error", e);
        } finally {
            // 优雅关闭
            worker.shutdownGracefully();
        }
    }

}
