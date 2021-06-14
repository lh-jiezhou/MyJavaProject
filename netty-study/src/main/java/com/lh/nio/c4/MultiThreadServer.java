package com.lh.nio.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 *  多线程实现
 *      boss: 负责建立连接
 *      selector: 负责处理读写事件
 */
@Slf4j
public class MultiThreadServer {

    public static void main(String[] args) throws IOException {

        // ServerSocketChannel
        Thread.currentThread().setName("boss");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // SelectionKey
        Selector boss = Selector.open();
        SelectionKey bossKey = ssc.register(boss, 0, null);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8080));
        // 1. 创建固定数量的 worker 并初始化 （一般设置为机器 cpu 核心数）Runtime.getRuntime().availableProcessors()
        Worker[] workers = new Worker[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger();

//        Worker worker = new Worker("worker-0");
        while (true) {
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                iter.remove();
                if(key.isAcceptable()){
                    // SocketChannel
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.debug("connected...{}", sc.getRemoteAddress());
                    // 2. 关联 selector
                    log.debug("before connected...{}", sc.getRemoteAddress());
//                    worker.register(sc);
                    // round robin(轮询) 轮流使用; （负载均衡算法）
                    workers[index.getAndIncrement() % workers.length].register(sc); // boss 调用
                    // 将下面一行 移至 worker 线程中；以便控制与 select() 的顺序
//                    sc.register(worker.selector, SelectionKey.OP_READ, null); // boss
                    log.debug("after connected...{}", sc.getRemoteAddress());


                }
            }
        }
    }

    static class Worker implements Runnable{
        private Thread thread;
        private Selector selector;
        private String name; // 线程名
        private volatile boolean start = false;
        // 方便两个线程之间传输数据;使用队列作为数据通道
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException{
            // 只执行一次，不要调一次就执行一次
            if(!start) { // 在调用者线程中 (boss )
                thread = new Thread(this, name);
                thread.start();
                selector = Selector.open();
                start = true;
            }
            // 向队列添加了任务，但这个任务并没有立即执行 boss
            queue.add( () -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null); // boss
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup(); // 唤醒 select 方法

//            // 或者直接; 不需要队列
//            selector.wakeup();
//            sc.register(selector, SelectionKey.OP_READ, null); // boss
        }

        @Override
        public void run() {  // 在 worker 线程中
            while(true) {
                try {
                    selector.select(); // worker-0 阻塞， (wakeup 可唤醒阻塞)
                    Runnable task = queue.poll();
                    if(task != null){
                        task.run(); // 这个位置 执行了 sc.register
                    }
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if(key.isReadable()){
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            log.debug("read...{}", channel.getRemoteAddress());
                            channel.read(buffer);
                            buffer.flip();
                            debugAll(buffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
