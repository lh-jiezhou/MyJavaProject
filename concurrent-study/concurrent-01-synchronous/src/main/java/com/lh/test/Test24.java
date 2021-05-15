package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 条件变量
 */
@Slf4j(topic = "c.Test24")
public class Test24 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    static ReentrantLock ROOM = new ReentrantLock();
    // 条件变量
    static Condition waitCigaretteSet = ROOM.newCondition();
    static Condition waitTakeoutSet = ROOM.newCondition();


    public static void main(String[] args) {


        new Thread(() -> {
//            synchronized (room) {
            ROOM.lock();
            try{
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
//                        room.wait();
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            } finally {
                ROOM.unlock();
            }

        }, "小南").start();

        new Thread(() -> {
            ROOM.lock();
            try {
                log.debug("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout) { // if 改成 while
                    log.debug("没外卖，先歇会！");
                    try {
//                        room.wait();
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");

            } finally {
                ROOM.unlock();
            }
        }, "小女").start();


        sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try{
                hasTakeout = true;
                log.debug("外卖到了噢！");
                waitTakeoutSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送外卖的").start();

        sleep(1);
        new Thread(() -> {
            ROOM.lock();
            try{
                hasCigarette = true;
                log.debug("烟到了噢！");
                waitCigaretteSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送烟的").start();


    }

}
