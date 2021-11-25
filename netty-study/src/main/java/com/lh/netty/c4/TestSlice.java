package com.lh.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.lh.netty.c4.TestByteBuf.log;

/**
 * 分片 Slice 体现【零拷贝】
 *      一般使用slice 都要使用retain() 对引用计数加一
 *      以防 在原buf 使用 release 后 分片不可使用
 *      slice 由自己进行release 释放
 *
 */
public class TestSlice {

    public static void main(String[] args) {

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        log(buf);

        // slice(index, length); 在切片的过程中没有发生数据复制 (同一内存)
        ByteBuf f1 = buf.slice(0, 5);
        ByteBuf f2 = buf.slice(5, 5);
        log(f1);
        log(f2);

        System.out.println("================");
        f1.setByte(0, 'b');
        log(f1);
        log(buf);

        // 切片后 会限制最大容量
//        f1.writeByte('x');

        // 对原有 buf 进行 release 释放 会影响切片后的 buf
        System.out.println("释放原有 ByteBuf 内存");
//        buf.release();
//        log(f1);
        // 可用 retain() 方法对原始引用计数加一 ( release 是减一 )
        f1.retain();
        buf.release();
        log(f1);


    }
}
