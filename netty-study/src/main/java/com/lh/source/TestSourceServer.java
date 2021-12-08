package com.lh.source;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * NIO 流程
 *  1. Selector selector = Selector.open();
 *          获取 selector ==> netty 中 EventLoop
 *  2. ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
 *          获取 channel ==> netty 中 AbstractBootstrap.doBind() 中 initAndRegister() 的 init
 *  3. SelectionKey selectionKey = serverSocketChannel.register(selector, 0, attachment);
 *          绑定 selector 和 channel ==> netty 中 AbstractBootstrap.doBind() 中 initAndRegister() 的 Register
 *  4. serverSocketChannel.bind(new InetSocketAddress(8080));
 *          绑定端口 (监听) ==> netty 中 AbstractBootstrap.doBind() 中 else 中 doBind0()
 *  5. selectionKey.interestOps(SelectionKey.OP_ACCEPT);
 *
 *  主线程   main
 *  NIO线程  nio-thread
 *
 */
public class TestSourceServer {

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler());
                    }
                }).bind(8080);
    }
}
