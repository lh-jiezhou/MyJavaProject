package com.lh.n4.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * ReentrantLock
 *    lockInterruptibly() 可打断 【 interrupt() 】 (被动的方式)
 *    lock() 不可打断 容易发生死锁
 */
@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) {
//        test2();
        test1();
    }

    private static void test2() {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            lock.lock();
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            sleep(1);
            t1.interrupt();
            log.debug("执行打断");
            sleep(1);
        } finally {
            log.debug("释放了锁");
            lock.unlock();
        }
    }

    private static void test1() {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            try {
                // 如果没有竞争 那么此方法就会获取 lock 对象锁
                // 如果有竞争就进入阻塞队列, 可以被其它线程用 interrupt 方法打断
                log.debug("尝试获得锁");
                lock.lockInterruptibly(); // 使用 lock() 不可打断
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("等锁的过程中被打断");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");


        lock.lock(); // 竞争
        log.debug("主线程获得了锁");
        t1.start();
        t1.interrupt();
//        try {
//            sleep(1);
//            t1.interrupt();
//            log.debug("执行打断");
//        } finally {
//            lock.unlock();
//        }
    }
}
