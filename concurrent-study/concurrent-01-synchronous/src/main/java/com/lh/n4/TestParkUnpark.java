package com.lh.n4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * park() 与 unpark()
 *   unpark() 即可以在park()之前调用也可以在park()之后调用
 *  都可以用于恢复某个线程的运行
 */
@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("start...");
//            sleep(1);
            sleep(2);
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();

//        sleep(2);
        sleep(1); // debug: Thread
        log.debug("unpark...");
        LockSupport.unpark(t1);
    }
}
