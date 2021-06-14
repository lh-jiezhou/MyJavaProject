package com.lh.nio.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static com.lh.nio.c1.TestByteBufferExam.split;

/**
 * 服务器端 非阻塞模式 + selector 防止cpu空转
 *      消息边界问题；读入数据 大于 ByteBuffer 时
 *      扩容问题； ByteBuffer 不能作为局部变量
 *          附件：sc.register(selector, 0, buffer);
 *
 */
@Slf4j
public class Server4 {



    public static void main(String[] args) throws IOException {

        // 1. 创建 selector , 管理多个 channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open(); // 获得服务器对象
        ssc.configureBlocking(false); // 切换为非阻塞模式 (影响accept方法)

        // 2. 建立 selector 与 channel 的联系 (注册)
        // SelectionKey 就是将来事件发生后，通过他可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // 表示 key 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key:{}" , sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while (true){
            // 3. select 方法; 没有事件时,线程阻塞；有事件发生时,线程才会恢复运行
            // 注意：在事件未处理时 也不会阻塞 (此例中 accept 未执行时)；不想处理可用 cancel 取消
            // 即 事件发生后 要么处理 要么取消 不能置之不理
            selector.select();

            // 4. 处理事件; selectedKeys 集合内部, 包含了所有发生的事件
            // 要想遍历时还可执行删除，用迭代器遍历
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator(); // accept, read

            while (iter.hasNext()){
                SelectionKey key = iter.next();
                // 处理key 的时候，要从 selectedKeys 集合中删除，否则下次处理会有问题
                iter.remove(); //
                log.debug("key:{}" , key);

                // 5. 区分事件类型
                if (key.isAcceptable()) { // 如果是 accept 事件
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();// 建立连接
                    sc.configureBlocking(false);

                    ByteBuffer buffer = ByteBuffer.allocate(4); // attachment 附件
                    // 将 ByteBuffer 作为附件关联到 selectionKey 上
                    SelectionKey scKey = sc.register(selector, 0, buffer);

                    scKey.interestOps(SelectionKey.OP_READ); // 关注读事件
                    log.debug("sc:{}", sc);
                    log.debug("scKey:{}", scKey);
                } else if(key.isReadable()) { // 如果是 read 事件
                    try {
                        SocketChannel channel = (SocketChannel) key.channel(); // 拿到事件的 channel

                        // 获取 selectionKey 上关联的附件
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        // 正常断开 read 方法返回值是 -1
                        int read = channel.read(buffer);// 客户端强制断开时（触发一次读事件），异常：远程主机强迫关闭了一个现有的连接。
                        if(read == -1){
                            key.cancel();
                        } else {
                            split(buffer); // TestByteBufferExam 中 消息拆分
                            if(buffer.position() == buffer.limit()){ // 一个内容都没有被读取掉
                                // 扩容
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer); // 原来的数据写入
                                key.attach(newBuffer); // 替换掉原来的附件
                            }
                        }
                        // key.attach(); // 关联新的附件
                    } catch (IOException e) {
                        // 异常断开时
                        e.printStackTrace();
                        // 要么处理，要么取消
                        key.cancel(); // 因为客户端断开了，因此需要将 key 取消(从selector的keys集合中真正删除 key)

                    }
                }

                // A. accept() 方法
//                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
//                SocketChannel sc = channel.accept();// 建立连接
//                log.debug("sc:{}", sc);
                // key.cancel();





            }


        }


    }
}
