package com.lh.concurrent;

import java.util.concurrent.Semaphore;

/**
 * LeetCode 交替打印
 *
 */
class FooBar {
    private int n;

    // 方法一：synchronized
    private Object obj = new Object();
    private volatile boolean fooExec = true;

    // 方法二：Semaphore
//     private Semaphore fooSema = new Semaphore(1);
    // private Semaphore barSema = new Semaphore(0);


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized (obj) {
                if(!fooExec){
                    obj.wait();
                }
                fooExec = false;
                // printFoo.run() outputs "foo". Do not change or remove this line.
        	    printFoo.run();
                obj.notifyAll();
            }
        }

        // 方法二
        // for (int i = 0; i < n; i++) {
        //     fooSema.acquire();
        //     printFoo.run();
        //     barSema.release();
        // }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized(obj){
                if(fooExec){
                    obj.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooExec = true;
                obj.notifyAll();
            }
        }

        // 方法二：Semaphore
        // for (int i = 0; i < n; i++) {
        //     barSema.acquire(); // 获取许可
        //     printBar.run();
        //     fooSema.release(); // 释放一个许可
        // }
    }
}