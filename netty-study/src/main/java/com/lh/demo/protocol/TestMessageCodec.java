package com.lh.demo.protocol;

import com.lh.demo.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * 使用 Embedded 测试
 *      LengthFieldBasedFrameDecoder 处理 黏包半包 情况
 *
 *      LengthFieldBasedFrameDecoder 记录多次读事件 状态信息, 线程不安全; LoggingHandler 无状态信息 线程安全
 *            查看源码是否有 @Sharable; 表示可以在多线程下 使用; 只需要创建一个实例即可
 *
 *
 */
public class TestMessageCodec {

    public static void main(String[] args) throws Exception {

        LoggingHandler LOGGING_HANDLER = new LoggingHandler();

        EmbeddedChannel channel = new EmbeddedChannel(
                LOGGING_HANDLER,
                // 处理 黏包半包 情况 // 最大长度，长度偏移，长度占用字节，长度调整，剥离字节数
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new MessageCodec());

        // Encode 往出站写;  编码 message -> ByteBuf
        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
//        channel.writeOutbound(message);

        // 准备 ByteBuf
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        // Decode 往入站写;  解码 ByteBuf -> message
//        channel.writeInbound(buf);


        // 使用 slice 切片; 测试半包情况
        ByteBuf s1 = buf.slice(0, 100);
        ByteBuf s2 = buf.slice(100, buf.readableBytes() - 100);

        // 引用计数先 +1
        s1.retain();
        // 注意 writeInbound() 执行完后 会调用 release 将引用计数置为0; 会将内存释放 故要调用  retain();
        channel.writeInbound(s1);
        channel.writeInbound(s2);



    }
}
