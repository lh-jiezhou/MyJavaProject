package com.lh.advance.c1;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * LTC解码器 解决黏包半包
 *
 */
public class TestLengthFieldDecoder {

    public static void main(String[] args) {
        // 看做服务器
        EmbeddedChannel channel = new EmbeddedChannel(

                // 最大长度，长度偏移，长度占用字节，长度调整，剥离字节数
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 4),
                new LoggingHandler(LogLevel.DEBUG)
        );


        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello, world");
        send(buffer, "Hi!");

        channel.writeInbound(buffer);


    }


    // 数据格式：【四个字节的内容长度，版本号，实际内容】
    private static void send(ByteBuf buffer, String content) {
        // 实际内容
        byte[] bytes = content.getBytes();
        // 实际内容长度
        int length = bytes.length;
        // 写入长度和内容
        buffer.writeInt(length);
        // 版本号
        buffer.writeByte(1);
        buffer.writeBytes(bytes);
    }
}
