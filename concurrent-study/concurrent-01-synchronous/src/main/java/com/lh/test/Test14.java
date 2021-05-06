package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * interrupt() 打断park() 让他继续运行
 *      打断后,打断标记为true， 此后再调用 park() 会失效
 *      可采用 Thread.interrupted() 返回标记并重置为 false
 */
@Slf4j(topic = "c.Test14")
public class Test14 {

    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park(); // 下面的代码将不会运行
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());

            LockSupport.park(); // 将失效
            log.debug("unpark2...");

            log.debug("返回打断状态并重置：{}", Thread.interrupted());
            LockSupport.park(); // 又生效
            log.debug("unpark3...");


        }, "t1");
        t1.start();

        sleep(1);
        t1.interrupt();
    }
}
