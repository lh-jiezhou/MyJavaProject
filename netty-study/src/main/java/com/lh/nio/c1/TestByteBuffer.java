package com.lh.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 新的API读取文件 data.txt
 *  FileChannel 获取
 *      1.输入输出流
 *      2.RandomAccessFile
 *
 *  快捷键：ctrl+Q 查看方法api
 *          ctrl+P 查看方法参数表
 */
@Slf4j // 日志输出
public class TestByteBuffer {

    public static void main(String[] args) {
        // FileChannel
        try( FileChannel channel = new FileInputStream("data.txt").getChannel() ) {
           // 准备缓冲区 (通过静态方法获得) allocate() 划分一块内存
            ByteBuffer buffer = ByteBuffer.allocate(10); // 十个字节
            while(true){ // 不加循环只能读取到缓冲区大小个数的数据
                // 从 channel 读取数据， 向 buffer 写入
                int len = channel.read(buffer);
                log.debug("读取到的字节数 {}", len);
                if(len == -1) break; // 没有内容了
                // 打印 buffer 内容
                buffer.flip(); // 切换至读模式
                while (buffer.hasRemaining()){ // 是否还有剩余
                    byte b = buffer.get(); // 一次读一个字节
                    log.debug("读取到的字节 {}", (char) b); // 转成字符
                }
                buffer.clear(); // 读完一次， buffer 切换为 写模式
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
