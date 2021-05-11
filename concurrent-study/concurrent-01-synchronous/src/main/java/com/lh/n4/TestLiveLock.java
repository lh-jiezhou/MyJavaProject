package com.lh.n4;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 活锁出现在两个线程互相改变对方的结束条件，最后谁也无法结束
 *      互相改变结束条件导致无法正常结束
 *  解决：
 *      交错睡眠时间
 */
@Slf4j(topic = "c.TestLiveLock")
public class TestLiveLock {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            // 期望减到 0 退出循环
            while (count > 0) {
                sleep(0.2);
                count--;
                log.debug("count: {}", count);
            }
        }, "t1").start();
        new Thread(() -> {
            // 期望超过 20 退出循环
            while (count < 20) {
//                sleep(0.2);
                sleep(0.4); // 交错或者随机睡眠时间,解决活锁
                count++;
                log.debug("count: {}", count);
            }
        }, "t2").start();
    }
}
