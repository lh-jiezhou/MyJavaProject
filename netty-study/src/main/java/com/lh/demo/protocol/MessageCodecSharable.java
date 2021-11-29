package com.lh.demo.protocol;

import com.lh.demo.config.Config;
import com.lh.demo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * MessageToMessageCodec 认为第一次拿到的消息就是完整的消息
 *      即 无状态
 *
 */
@Slf4j
@ChannelHandler.Sharable
/**
 * 必须和 LengthFieldBasedFrameDecoder 一起使用, 确保接到的 ByteBuf 消息是完整的
 */
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outList) throws Exception {
        ByteBuf out = ctx.alloc().buffer();

        // ByteBuf 中写入内容
        // 1. 4 个字节的魔数
        out.writeBytes(new byte[]{1,2,3,4});
        // 2. 1 个字节的版本;
        out.writeByte(1);
        // 3. 1 个字节的序列化方式;  0 jdk序列化; 1 json序列化
//        out.writeByte(0);
        out.writeByte(Config.getSerializerAlgorithm().ordinal());
        // 4. 1 字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4 个字节的请求序号
        out.writeInt(msg.getSequenceId());
        // 无意义，对其填充用 2的次方
        out.writeByte(0xff);

        // 6. 获取对象的字节数组 (msg 转为 ByteBuf )
    /*    ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();*/
        // 调用 自己实现的序列化 (可扩展性)
        byte[] bytes = Config.getSerializerAlgorithm().serialize(msg);


        // 7. 长度
        out.writeInt(bytes.length);
        // 8. 写入内容 (消息正文)
        out.writeBytes(bytes);

        outList.add(out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 参照编码 逆过程

        // 1. 读4个字节 魔数
        int magicNum = in.readInt();
        // 2. 版本
        byte version = in.readByte();
        // 3. 序列化方式 0 or 1
        byte serializerAlgorithm = in.readByte();
        // 4. 指令类型 0, 1, 2, 3, 4 ...
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
       /* ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();*/
        // 调用自己实现的反序列化
//        Message message = Serializer.Algorithm.Java.deserialize(Message.class, bytes);

        // 找到反序列化时的算法
        Serializer.Algorithm algorithm = Serializer.Algorithm.values()[serializerAlgorithm];
        // 确定具体消息类型
        Class<?> messageClass = Message.getMessageClass(messageType);
        Object deserialize = algorithm.deserialize(messageClass, bytes);

//        log.debug("{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, sequenceId, length);
//        log.debug("Message: {}", message);
        // 加入到 List
        out.add(deserialize);
    }
}
