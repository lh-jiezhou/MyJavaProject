package com.lh.n4.deadlock;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 死锁
 *  线程1 在获取A对象的锁时还期望得到B对象的锁
 *  线程2 在获取B对象的锁时还期望得到A对象的锁
 */
@Slf4j(topic = "c.TestDeadLock")
public class TestDeadLock {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("lock A");
                sleep(1);
                synchronized (B) {
                    log.debug("lock B");
                    log.debug("操作...");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("lock B");
                sleep(0.5);
                synchronized (A) {
                    log.debug("lock A");
                    log.debug("操作...");
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}

