package com.lh.nio.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * 服务器端 阻塞模式 + 单线程
 *      使用 NIO 来理解阻塞模式 (阻塞：干一件事时 无法干其他事)
 *      accept(); // 阻塞方法，没建立连接时， 线程停止运行
 *      read(buffer); // 阻塞方法，没读入数据时， 线程停止运行
 */
@Slf4j
public class Server {

    //
    public static void main(String[] args) throws IOException {

        // 使用 NIO 来理解阻塞模式 (单线程)
        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open(); // 获得服务器对象

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            // 4. accept 建立与客户端连接; SocketChannel 用来与客户端之间通信
            log.debug("connecting...");
            SocketChannel sc = ssc.accept(); // 阻塞方法，线程停止运行
            log.debug("connected... {}", sc);
            channels.add(sc);

            for(SocketChannel channel: channels){
                // 5. 接收客户端发送的数据
                log.debug("before read... {}", channel);
                channel.read(buffer); // 阻塞方法，线程停止运行
                buffer.flip();
                debugAll(buffer);
                buffer.clear();
                log.debug("after read... {}", channel);
            }

        }


    }
}
