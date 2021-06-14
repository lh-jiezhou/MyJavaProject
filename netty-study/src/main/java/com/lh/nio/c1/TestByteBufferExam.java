package com.lh.nio.c1;

import java.nio.ByteBuffer;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * 处理 黏包 和 半包 的方法
 *   网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
 *   但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
 *      Hello,world\n
 *      I'm zhangsan\n
 *      How are you?\n
 *    变成了下面的两个 byteBuffer （黏包 和 半包 现象）
 *      Hello,world\nI'm zhangsan\nHo    （黏包）
 *      w are you?\n   （半包）
 *    现在要求编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
 *
 */
public class TestByteBufferExam {

    public static void main(String[] args) {

        // 模拟分两次接收数据
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    public static void split(ByteBuffer source){
        source.flip(); // 读模式
        for(int i = 0; i < source.limit(); i++){
            // 找到一条完整信息
            if(source.get(i) == '\n'){
                int length = i + 1 - source.position();
                // 把这条完整信息存入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for(int j = 0; j < length; j++){
                    target.put(source.get());
                }
                debugAll(target);
            }
        }

        source.compact(); // 写模式 （剩余部分前移动）
    }
}
