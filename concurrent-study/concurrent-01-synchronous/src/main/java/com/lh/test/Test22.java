package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 锁的使用
 *    对象级别 (synchronized 为关键字级别)
 *    1、可重入性
 *    2、可打断  lockInterruptibly();
 *
 */
@Slf4j(topic = "c.Test22")
public class Test22 {

    private static ReentrantLock lock = new ReentrantLock(); // 要新建对象
    public static void main(String[] args) {
        // lock对象 取代了synchronized时的普通对象+monitor
        lock.lock(); // 获取锁
        try{ // 临界区
            log.debug("enter main");
            m1();
        } finally { // 释放锁
            lock.unlock();
        }


    }

    public static void m1() {
        lock.lock();  // 重入
        try {
            log.debug("enter m1");
            m2();
        } finally {
            lock.unlock(); // 对应一个 lock()
        }
    }

    public static void m2() {
        lock.lock(); // 重入
        try {
            log.debug("enter m2");
        } finally {
            lock.unlock();
        }
    }


}
