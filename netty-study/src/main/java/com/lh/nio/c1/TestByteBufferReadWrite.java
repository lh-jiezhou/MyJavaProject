package com.lh.nio.c1;

import java.nio.ByteBuffer;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * buffer 的读/写模式
 *
 */
public class TestByteBufferReadWrite {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 写
        buffer.put((byte) 0x61); // 写入内容 'a'
        debugAll(buffer); // 工具类中的方法

        buffer.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(buffer);

        // 读 (position位置开始读)
        buffer.flip(); // 切换读模式
        System.out.println(buffer.get());
        debugAll(buffer);

        buffer.compact(); // 切换写模式
        debugAll(buffer);

        // 写
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
}
