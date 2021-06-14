package com.lh.nio.c1;

import java.nio.ByteBuffer;

/** 输出
 * class java.nio.HeapByteBuffer;  // allocate()
 *   - java 堆内存，读写效率较低，受到GC的影响
 * class java.nio.DirectByteBuffer;  // allocateDirect()
 *   - 直接内存，读写效率较高(少一次拷贝)，不受GC影响
 *   - 分配内存的效率低
 *   - 可能发生内存泄漏
 */
public class TestByteBufferAllocate {

    public static void main(String[] args) {

        // 打印类型
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

    }
}
