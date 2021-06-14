package com.lh.nio.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 两个Channel传输数据
 *   transferTo() 方法；效率高，底层会利用操作系统的零拷贝进行优化
 *    局限：一次最多传 2g 的文件
 */
public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try(
            FileChannel from = new FileInputStream("data.txt").getChannel();
            FileChannel to = new FileOutputStream("to.txt").getChannel();
        ){
            long size = from.size();
            // left 表示还剩余多少字节 （改进可传2g以上的文件；分次）
            for(long left = size; left > 0; ){
                // 效率高，底层会利用操作系统的零拷贝进行优化
//                from.transferTo(0, from.size(), to);
                System.out.println("position: " + (size-left) + " left: " + left);
                left -= from.transferTo((size-left), left, to); //
            }


        } catch (IOException e){
             e.printStackTrace();
        }
    }

}
