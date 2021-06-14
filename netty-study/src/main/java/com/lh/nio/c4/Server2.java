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
 * 服务器端 非阻塞模式 + 单线程
 *      此例中 非阻塞循环过于频繁 （使用selector 改进）
 *      ssc.configureBlocking(false);
 *      sc.configureBlocking(false);
 */
@Slf4j
public class Server2 {

    //
    public static void main(String[] args) throws IOException {

        // 使用 NIO 来理解阻塞模式
        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        // 1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open(); // 获得服务器对象
        ssc.configureBlocking(false); // 切换为非阻塞模式 (影响accept方法)
        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            // 4. accept 建立与客户端连接; SocketChannel 用来与客户端之间通信
            SocketChannel sc = ssc.accept(); // 非阻塞 线程继续运行，但如果没有连接建立，accept 返回 null
            if(sc != null){
                log.debug("connected... {}", sc);
                sc.configureBlocking(false); // 切换为非阻塞模式 (影响read方法)
                channels.add(sc);
            }

            for(SocketChannel channel: channels){
                // 5. 接收客户端发送的数据
                int read = channel.read(buffer); // 非阻塞方法，线程继续运行，如果没有读到数据，read返回0
                if(read > 0){
                    buffer.flip();
                    debugAll(buffer);
                    buffer.clear();
                    log.debug("after read... {}", channel);
                }
            }

        }


    }
}
