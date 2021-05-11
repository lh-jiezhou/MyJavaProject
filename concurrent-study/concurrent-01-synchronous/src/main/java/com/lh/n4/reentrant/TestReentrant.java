package com.lh.n4.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock 锁的使用
 *    对象级别 (synchronized 为关键字级别)
 *    1、可重入性
 *    2、可打断  lockInterruptibly();
 *
 */
@Slf4j(topic = "c.TestReentrant")
public class TestReentrant {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        // lock对象 取代了synchronized时的普通对象+monitor
        lock.lock();
        try {
            log.debug("execute method1");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public static void method2() {
        lock.lock();
        try {
            log.debug("execute method2");
            method3();
        } finally {
            lock.unlock();
        }
    }

    public static void method3() {
        lock.lock();
        try {
            log.debug("execute method3");
        } finally {
            lock.unlock();
        }
    }
}
