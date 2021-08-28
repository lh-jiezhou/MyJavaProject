package com.lh.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LeetCode 1114  按序打印
 *  方法一：使用原子整数
 *  方法二：使用 CountDownLatch()
 *  方法三：使用 synchronized加锁; notifyAll + wait
 *
 */
public class Foo {

//    // 1.
//    private AtomicInteger firstJobDone;
//    private AtomicInteger secondJobDone;
//
//    public Foo() {
//        firstJobDone = new AtomicInteger(0);
//        secondJobDone = new AtomicInteger(0);
//    }

//    public void first(Runnable printFirst) throws InterruptedException {
//
//        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        firstJobDone.incrementAndGet();
//    }
//
//    public void second(Runnable printSecond) throws InterruptedException {
//        while( firstJobDone.get() != 1){
//
//        }
//        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        secondJobDone.incrementAndGet();
//    }
//
//    public void third(Runnable printThird) throws InterruptedException {
//        while(secondJobDone.get() != 1){
//
//        }
//        // printThird.run() outputs "third". Do not change or remove this line.
//        printThird.run();
//    }


    // 2.
    private CountDownLatch c1;
    private CountDownLatch c2;

    public Foo() {
        c1 = new CountDownLatch(1);
        c2 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        c1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        c1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        c2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        c2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
