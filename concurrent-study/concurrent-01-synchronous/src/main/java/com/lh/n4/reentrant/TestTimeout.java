package com.lh.n4.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 锁超时
 *    tryLock()
 *    主动防止锁 死等 从而避免死锁
 */
@Slf4j(topic = "c.TestTimeout")
public class TestTimeout {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    private static void test1() {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.debug("启动...尝试获取锁");
            try {
                if (!lock.tryLock(2, TimeUnit.SECONDS)) { // 尝试获得锁并 等待2秒
                    log.debug("获取等待 1s 后失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("获取不到锁");
                return;
            }

            try { // 临界区
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("主线程获得了锁");
        t1.start();
        try {
            sleep(1);
        } finally {
            log.debug("主线程释放了锁");
            lock.unlock();
        }
    }

    private static void test2() {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.debug("启动...尝试获得锁");
            if (!lock.tryLock()) { // 返回Boolean值
                log.debug("获取立刻失败，返回");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("主线程获得了锁");
        t1.start();
        try {
            sleep(2);
        } finally {
            lock.unlock();
        }
    }
}
