package com.lh.test;

import com.lh.n2.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用ReentrantLock中 tryLock() 解决哲学家问题中的死锁
 *      tryLock()获取锁失败 会释放之前的锁
 */
@Slf4j(topic = "c.Test23")
public class Test23 {
    public static void main(String[] args) {
        // 五个筷子
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");
        // 五个哲学家
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("柏拉图", c2, c3).start();
        new Philosopher("亚里士多德", c3, c4).start();
        new Philosopher("赫拉克利特", c4, c5).start();
        new Philosopher("阿基米德", c5, c1).start();
//        new Philosopher("阿基米德", c1, c5).start(); // 顺序加锁
    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread { // 继承线程类
    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() { //
        while (true) {
            // 采用 tryLock() 避免死锁
            //　尝试获得左手筷子
//            synchronized (left) {
            if(left.tryLock()){ // true 表示获得到锁
                try {
                    // 尝试获得右手筷子
//                    synchronized (right) {
                    if(right.tryLock()){
                        try {
                            // 获得两个筷子
                            eat(); // 吃饭
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock(); // 释放手里的筷子
                }
            }
        }
    }

    Random random = new Random();
    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(0.5); // 相当于思考
    }
}

class Chopstick extends ReentrantLock { // 继承，把筷子单词锁
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}
