package com.lh.nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class WriteServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT); // ServerSocketChannel 关注 accept 事件即可

        ssc.bind(new InetSocketAddress(8080)); // 监听端口

        while(true){
            selector.select(); // 阻塞; 有事件发生时才向下
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);

                    // 1. 向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 5000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());

//                    while (buffer.hasRemaining()) { // 效率不高
//                        // 2. write 返回值 代表实际写入的字节数
//                        int write = sc.write(buffer); // 写入，不能保证一次将所有数据都写到客户端
//                        System.out.println(write);
//                    }
                    // 把 while 循环 变成多次对可写事件的处理

                    // 2. write 返回值 代表实际写入的字节数
                    int write = sc.write(buffer); // 写入，不能保证一次将所有数据都写到客户端
                    System.out.println(write);


                    // 3. 判断是否有剩余内容
                    if (buffer.hasRemaining()) {
                        // 4. 关注可写事件
                        scKey.interestOps(scKey.interestOps() + SelectionKey.OP_WRITE);
//                        scKey.interestOps(scKey.interestOps() | SelectionKey.OP_WRITE); // 位运算写法

                        // 5. 把未写完的数据 挂到 scKey 上
                        scKey.attach(buffer);

                    }

                } else if(key.isWritable()){
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    System.out.println(write);

                    // 6. 清理操作
                    if (!buffer.hasRemaining()){
                        key.attach(null); // 如果内容写完；清除 buffer
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE); // 取消关注可写事件
                    }
                }
            }
        }
    }
}
