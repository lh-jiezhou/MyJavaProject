package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

import static com.lh.n2.util.Sleeper.sleep;


/**
 * 原子累加器 原理
 *      CAS 实现锁锁原理
 *
 */
@Slf4j(topic = "c.Test42")
public class LockCas {
    // 0 没加锁
    // 1 加锁
    private AtomicInteger state = new AtomicInteger(0);

    public void lock() {
        // 尝试 无锁 -> 加锁
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    // 解锁
    public void unlock() {
        log.debug("unlock...");
        state.set(0);
    }

    public static void main(String[] args) {
        LockCas lock = new LockCas();
        new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
                sleep(1);
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
