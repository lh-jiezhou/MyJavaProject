package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 *  第五章：JMM（Java 内存模型）
 *
 *  可见性问题： 一个线程修改了主存中的数据对另外的线程不可见
 *     可见性：保证指令不受cpu缓存影响
 *
 *   volatile(易变)：表示修饰的变量不能从缓存中读取, 每次需要从主存中读取
 *   或者加锁 synchronized 也可以保持 可见性 ； 但操作较为重量级
 */
@Slf4j(topic = "c.Test32")
public class Test32 {

//    static boolean run = true; // 存在可见性问题
    volatile static boolean run = true; // 不会从缓存中获取run值

    // 或者加锁 也可以保持 可见性 synchronized； 但操作较为重量级
    // 锁对象
//    final static Object lock = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (run){
                // 加入输出语句 也可保证可见性 因为 println中有加锁
//                System.out.println();
                if (!run){
                    break;
                }
            }
        });

        t.start();
        System.out.println(Math.round(-4.1));
        sleep(1);
        log.debug("停止 t线程");
        run = false; // 线程t不会如预想停下来
    }

}
