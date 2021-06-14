package com.lh.nio.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 * 字符串与ByteBuffer相互转换
 *  String 转 buffer
 *   1."hello".getBytes()
 *   2. StandardCharsets.UTF_8.encode()
 *   3. wrap (完成后 自动切换到读模式)
 *  buffer 转 String
 *   4. StandardCharsets.UTF_8.encode()
 */
public class TestByteBufferString {

    public static void main(String[] args) {
        // 1. 字符串转为 ByteBuffer
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes()); // 写
        debugAll(buffer1);

        // 2. Charset (完成后 自动切换到读模式)
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);

        // 3. wrap (完成后 自动切换到读模式)
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);

        // =================== ByteBuffer 转 String
        String str2 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str2);

        // decode 要先切换为读模式
        buffer1.flip();
        String str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);



    }
}
