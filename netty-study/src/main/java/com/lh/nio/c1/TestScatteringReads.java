package com.lh.nio.c1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.lh.util.ByteBufferUtil.debugAll;

/**
 *  分散读取 Scattering Reads
 *    减少数据复制次数, 提高效率
 */
public class TestScatteringReads {

    public static void main(String[] args) {
        //
        try (FileChannel channel = new RandomAccessFile("3parts.txt", "r").getChannel()) {
            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            debugAll(a);
            debugAll(b);
            debugAll(c);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
