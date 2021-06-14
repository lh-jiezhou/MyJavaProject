package com.lh.nio.c1;

import java.nio.ByteBuffer;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * 从buffer中读数据的方式
 *   1. 调用 channel 的 write 方法
 *   2. 调用 buffer 自己的 get 方法
 *   rewind
 *   mark & reset
 */
public class TestByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

//        buffer.get(new byte[4]);
//        debugAll(buffer);
//        // rewind 从头开始读
//        buffer.rewind();
//        System.out.println((char) buffer.get());

        // mark 和 reset
        // mark 做一个标记 记录 position 位置
        // reset 将 position 重置到 mark 位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark(); // 加标记 索引为 2 的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.reset(); // position 重置到 索引 2
//        System.out.println((char) buffer.get());

        // get(i); 不会改变读索引的位置 position
        System.out.println((char) buffer.get(3));
        debugAll(buffer);



    }
}
