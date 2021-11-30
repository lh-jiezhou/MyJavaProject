package com.lh.demo.client;

import com.lh.demo.client.handler.RpcResponseMessageHandler;
import com.lh.demo.message.RpcRequestMessage;
import com.lh.demo.protocol.MessageCodecSharable;
import com.lh.demo.protocol.ProcotolFrameDecoder;
import com.lh.demo.protocol.SequenceIdGenerator;
import com.lh.demo.server.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * 优化 简化操作
 *      获取 唯一的 channel 对象
 */
@Slf4j
public class RpcClientManager {

    public static void main(String[] args) {

        // 太复杂
//        RpcRequestMessage message = new RpcRequestMessage(
//                1,
//                "com.lh.demo.server.service.HelloService",
//                "sayHello",
//                String.class,
//                new Class[]{String.class},
//                new Object[]{"张三"}
//        );
//        getChannel().writeAndFlush(message);

        // 希望简化成
        HelloService service = getProxyService(HelloService.class);
        System.out.println(service.sayHello("zhangsan"));
//        System.out.println(service.sayHello("lisi"));
//        System.out.println(service.sayHello("wangwu"));


    }

    // 创建代理类 (由代理类 拿到channel发送消息)
    public static <T> T getProxyService(Class<T> serviceClass) {
        // 类加载器
        ClassLoader loader = serviceClass.getClassLoader();
        // 代理类要实现的接口
        Class<?>[] interfaces = new Class[]{serviceClass};
        Object o = Proxy.newProxyInstance(loader, interfaces, (proxy, method, args) -> {
            // 1. 将方法调用转换为 消息对象
            int sequenceId = SequenceIdGenerator.nextId();
            RpcRequestMessage msg = new RpcRequestMessage(
                    sequenceId,
                    serviceClass.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );
            // 2. 将消息对象发送出去
            getChannel().writeAndFlush(msg);

            // 3. 准备一个空 Promise 对象(容器), 来接收结果     指定Promise异步接收结果的线程
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISES.put(sequenceId, promise);

//            promise.addListener(future -> {
//
//            });

            // 4. 等待 promise 结果 (异步的网络调用 用同步来等待结果)
            promise.await();

            // 5. 返回 正常 / 失败
            if(promise.isSuccess()){
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
        return (T) o;
    }



    private static Channel channel = null;
    private static final Object LOCK = new Object();

    // 单例模式 ( + 双重检查锁 )
    public static Channel getChannel() {
        if(channel != null){
            return channel;
        }
        // 加锁 安全保护 (双重检查锁)
        synchronized (LOCK) { // t2
            if(channel != null){ // t1
                return channel;
            }
            initChannel();
            return channel;
        }
    }


    /**
     * 初始化 Channel
     *      长连接 只执行一次即可 (使用单例模式)
     */
    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProcotolFrameDecoder());
                ch.pipeline().addLast(LOGGING_HANDLER);
                ch.pipeline().addLast(MESSAGE_CODEC);
                ch.pipeline().addLast(RPC_HANDLER);
            }
        });
        try {
            channel = bootstrap.connect("localhost", 8080).sync().channel();
            // 注意 使用 addListener 异步
            channel.closeFuture().addListener(future -> {
                // channel 关闭时触发
                group.shutdownGracefully();
            });
        } catch (Exception e) {
            log.error("client error", e);
        }
    }
}