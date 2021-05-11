package com.lh.n4;

import lombok.extern.slf4j.Slf4j;

import static com.lh.n2.util.Sleeper.sleep;

/**
 *  if 改成 while
 *     防止虚假唤醒
 *     真正解决问题
 *
 *     wait() 与 notify() 的正确使用方式
 *     synchronized(lock) {
 *          while(条件不成立) {
 *               lock.wait();
 *          }
 *          // 干活
 *     }
 *      //另一个线程
 *      synchronized(lock) {
 *           lock.notifyAll();
 *      }
 */

@Slf4j(topic = "c.TestCorrectPosture")
public class TestCorrectPostureStep5 {
    static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {


        new Thread(() -> {
            synchronized (room) {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？[{}]", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活了");
                } else {
                    log.debug("没干成活...");
                }
            }
        }, "小南").start();

        new Thread(() -> {
            synchronized (room) {
                Thread thread = Thread.currentThread();
                log.debug("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout) { // if 改成 while
                    log.debug("没外卖，先歇会！");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("外卖送到没？[{}]", hasTakeout);
                if (hasTakeout) {
                    log.debug("可以开始干活了");
                } else {
                    log.debug("没干成活...");
                }
            }
        }, "小女").start();

        sleep(1);
        new Thread(() -> {
            synchronized (room) {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                room.notifyAll();
            }
        }, "送外卖的").start();


    }

}
