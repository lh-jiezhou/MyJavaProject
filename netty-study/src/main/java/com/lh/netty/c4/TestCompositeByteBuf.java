package com.lh.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static com.lh.netty.c4.TestByteBuf.log;

/**
 * Composite 将多个小的 ByteBuf 组合成大的 ByteBuf
 *      不会发生数据的复制
 *      避免内存复制
 *
 *      需要注意 release 问题
 *
 */
public class TestCompositeByteBuf {

    public static void main(String[] args) {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[]{6, 7, 8, 9, 10});

        /*ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        // writeBytes 会发生真正的数据复制
        buffer.writeBytes(buf1).writeBytes(buf2);
        log(buffer);*/

        // compositeBuffer 不会复制数据; compositeBuffer 不会调整写入指针位置 需要加 true 参数
        CompositeByteBuf buffer = ByteBufAllocator.DEFAULT.compositeBuffer();
        buffer.addComponents(true, buf1, buf2);
        log(buffer);

    }
}
