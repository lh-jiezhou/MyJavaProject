package com.lh.demo.protocol;

import com.lh.demo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 自定义编解码 (协议解析)
 *      继承 ByteToMessageCodec<> 泛型指定消息类型
 *
 *      ByteToMessageCodec 父类有限制; 其子类不能加 Sharable 注解
 */
@Slf4j
//@ChannelHandler.Sharable // ByteToMessageCodec 父类有限制不能加 Sharable 注解
public class MessageCodec extends ByteToMessageCodec<Message> {

    // 编码 Message -> ByteBuf
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {

        // ByteBuf 中写入内容
        // 1. 4 个字节的魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 2. 1 个字节的版本;
        out.writeByte(1);
        // 3. 1 个字节的序列化方式;  0 jdk序列化; 1 json序列化
        out.writeByte(0);
        // 4. 1 字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4 个字节的请求序号
        out.writeInt(msg.getSequenceId());
        // 无意义，对其填充用 2的次方
        out.writeByte(0xff);
        // 6. 获取对象的字节数组 (msg 转为 ByteBuf )
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();
        // 7. 长度
        out.writeInt(bytes.length);

        // 8. 写入内容 (消息正文)
        out.writeBytes(bytes);

    }

    // 解码 ByteBuf -> Message
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 参照编码 逆过程

        // 1. 读4个字节 魔数
        int magicNum = in.readInt();
        // 2. 版本
        byte version = in.readByte();
        // 3. 序列化方式
        byte serializerType = in.readByte();
        // 4. 指令类型
        byte messageType = in.readByte();
        // 5. 消息序号
        int sequenceId = in.readInt();
        // 无意义填充
        in.readByte();
        // 6. 长度
        int length = in.readInt();
        // 7. 消息内容
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);

        // 8. Byte数组 反序列化 成 Message 对象
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();

        log.debug("{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("Message: {}", message);

        // 加入到 List
        out.add(message);

    }
}
