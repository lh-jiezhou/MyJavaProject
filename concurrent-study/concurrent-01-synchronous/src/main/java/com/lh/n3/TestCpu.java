package com.lh.n3;

/**
 * while(true) 中 加 sleep()
 *  防止空转 在单核情况下占满CPU
 *  (可以用wait 或 条件变量达到类似效果，但需要加锁)
 *  sleep() 不需要加锁
 */
public class TestCpu {
    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                /*try {
                    Thread.sleep(1); // 防止空转,浪费资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }
}
