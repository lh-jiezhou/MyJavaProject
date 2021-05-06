package com.lh.n4;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 线程八锁问题 (八种加锁情况)
 *  考察 synchronized 锁住的是哪个对象
 *
 */
@Slf4j(topic = "c.Test8Locks")
public class Test8Locks {
    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(() -> {
            log.debug("begin");
            n1.a();
        }).start();
        new Thread(() -> {
            log.debug("begin");
            n2.b();
        }).start();
    }
}
@Slf4j(topic = "c.Number")
class Number{
    // 成员方法, 锁住的是this对象
    public synchronized void a() {
        sleep(1);
        log.debug("1");
    }
    public synchronized void b() {
        log.debug("2");
    }

    public void c(){ // 不加synchronized (无互斥效果)
        log.debug("3");
    }
}
