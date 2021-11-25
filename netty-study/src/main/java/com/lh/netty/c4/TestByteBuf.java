package com.lh.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.StandardCharsets;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * ByteBuf 的创建
 * ByteBuf 的内存
 *      堆内存：分配效率高，读写效率低
 *      直接内存：分配效率低，读写效率高 (默认)
 * ByteBuf 池化
 *      Netty 4.1 之后默认为池化
 *      池化
 *      非池化
 *      -Dio.netty.allocator.type={unpooled|pooled} 虚拟机参数 改变池化设置
 *
 *      buffer()： PooledUnsafeDirectByteBuf 池化直接内存
 *      heapBuffer()：PooledUnsafeHeapByteBuf 池化堆内存
 */
public class TestByteBuf {

    public static void main(String[] args) {

        // 默认容量 256 字节; 会自动扩容
//        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer();
        System.out.println(buf.getClass());
        log(buf);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 300; i++) {
            sb.append("a");
        }
        buf.writeBytes(sb.toString().getBytes());
        log(buf);
    }

    public static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity:").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, buffer);
        System.out.println(buf.toString());
    }
}
