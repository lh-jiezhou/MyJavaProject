package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * wait(),notify() 方法要先拥有锁才能调用
 *
 */
@Slf4j(topic = "c.Test18")
public class Test18 {

    static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized(lock) {
            try {
                lock.wait(); // IllegalMonitorStateException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
