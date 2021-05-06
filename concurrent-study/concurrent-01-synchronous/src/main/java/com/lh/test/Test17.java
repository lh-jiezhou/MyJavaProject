package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 分时系统，上下文切换
 *      可能产生指令间的交错
 *  多线程访问共享资源时产生的安全问题
 *
 *
 * 将 synchronized 写在普通成员方法上 (表示锁this对象)
 * 将 synchronized 写在静态方法上 (表示锁类对象对象，Test.class)
 *
 */

@Slf4j(topic = "c.Test17")
public class Test17 {

//    static int counter = 0;
//    static Object lock = new Object();



    public static void main(String[] args) throws InterruptedException {
        // 优化写法 面向对象
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
//                counter++; // 临界区
//                synchronized (lock){ // 此例对象只用于加锁
//                    counter++; // 临界区
//                }
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
//                counter--; // 临界区
//                synchronized (lock){ // 此例对象只用于加锁
//                    counter--; // 临界区
//                }
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",room.getCounter());
    }
}

class Room {
    private int counter = 0;

//    public void increment() {
//        synchronized (this){
//            counter++;
//        }
//    }
    // 将 synchronized 写在方法上 (表示锁this对象)
    // 等价改造
    public synchronized void increment(){
        counter++;
    }

    public void decrement() {
        synchronized (this){
            counter--;
        }
    }

    public int getCounter() {
        synchronized (this){
            return counter;
        }
    }
}
