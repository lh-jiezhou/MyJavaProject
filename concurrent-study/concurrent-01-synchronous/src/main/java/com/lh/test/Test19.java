package com.lh.test;

import com.lh.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * wait() 与 sleep() 方法
 *      sleep()不会释放锁
 *      wait()会释放锁
 *
 */
@Slf4j(topic = "c.Test19")
public class Test19 {

    // 锁的对象建议都改成final ,表示引用不可变
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                try {
//                    Thread.sleep(20000);
                    lock.wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        Sleeper.sleep(1);
        synchronized (lock) {
            log.debug("获得锁");
        }
    }
}
