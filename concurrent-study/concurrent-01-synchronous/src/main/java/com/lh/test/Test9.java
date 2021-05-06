package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * yield() 方法
 *  让出CPU 进入 Runnable态
 * setPriority() 方法
 *  设置线程优先级
 */
@Slf4j(topic = "c.Test9")
public class Test9 {

    public static void main(String[] args) {

        Runnable task1 = () -> {
            int count = 0;
            for (;;){
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (;;){
                Thread.yield(); // 让出CPU时间片
                System.out.println("      ---->2 "  + count++);
            }
        };

        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");

        // 优先级
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }

}
