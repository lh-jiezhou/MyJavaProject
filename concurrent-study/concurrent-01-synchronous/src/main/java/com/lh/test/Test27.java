package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 同步模式-交替输出
 *     1、使用wait(), notify(); !! 设置等待标记 flag
 *     2、使用 Lock 条件变量版 (ReentrantLock 多间休息室) 【Test30.java】
 *     3、使用 park() , unpark() 【Test31.java】
 *
 * 线程 1 输出 a 5 次，线程 2 输出 b 5 次，线程 3 输出 c 5 次。
 * 现在要求输出 abcabcabcabcabc 怎么实现
 */

@Slf4j(topic = "c.Test27")
public class Test27 {

    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1, 5);

        new Thread(() -> {
            wn.print("a", 1, 2);
        }).start();
        new Thread(() -> {
            wn.print("b", 2, 3);
        }).start();
        new Thread(() -> {
            wn.print("c", 3, 1);
        }).start();
    }

}

class WaitNotify{
    // 等待标记
    private int flag;
    // 循环次数
    private int loopNumber;

    // 打印 a b c
    public void print(String str, int waitFlag, int nextFlag){
        for(int i=0; i<loopNumber; i++){
            synchronized (this) {
                while( flag != waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str); // 输出内容
                flag = nextFlag; // 更新flag
                this.notifyAll();
            }
        }
    }

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }
}
