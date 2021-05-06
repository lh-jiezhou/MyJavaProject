package com.lh.n3;


import com.lh.Constants;

import com.lh.n2.util.FileReader;

/**
 * 线程的状态
 *  操作系统层面：五种状态
 *  Java API层面：六种状态； Thread.State 枚举类型
 *
 *  操作系统层面的阻塞状态 在JavaAPI层面还是 Runnable
 */
public class TestState2 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            FileReader.read(Constants.MP4_FULL_PATH);
            FileReader.read(Constants.MP4_FULL_PATH);
            FileReader.read(Constants.MP4_FULL_PATH);
        }, "t1").start();

//        Thread.sleep(1000);
        System.out.println("ok"); // 断点设为Thread模式
    }
}
