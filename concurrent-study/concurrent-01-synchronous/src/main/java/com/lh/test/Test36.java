package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import static com.lh.n2.util.Sleeper.sleep;

/**
 * 原子引用
 *      ABA问题
 * compareAndSet
 *      可判断是否一致
 *      是否可以判断 共享变量是否被修改过
 *
 * 怎么让主线程感知到其他线程是否对共享变量修改过(修改过多少次)？
 *      比较值的同时 再加一个 版本号 getStamp()
 *      AtomicStampedReference
 *
 * 让主线程感知到其他线程是否对共享变量修改过？
 *      AtomicMarkableReference 用Boolean值记录是否修改过
 *
 */
@Slf4j(topic = "c.Test36")
public class Test36 {

//    static AtomicReference<String> ref = new AtomicReference<>("A");
    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);


    public static void main(String[] args) {
        log.debug("main start...");
        // 获取值
//        String prev = ref.get();
        String prev = ref.getReference();
        // 获取版本号
        int stamp = ref.getStamp();
        log.debug("{}", stamp);

        other();
        sleep(1);
        // 尝试改为 C
//        log.debug("change A -> C {}", ref.compareAndSet(prev, "C"));
        log.debug("{}", stamp);
        log.debug("change A -> C {}", ref.compareAndSet(prev, "C", stamp, stamp+1));

    }

    private static void other() {
        new Thread(() -> {
//            log.debug("change A->B {}", ref.compareAndSet(ref.get(), "B"));
            int stamp = ref.getStamp();
            log.debug("{}", stamp);
            log.debug("change A->B {}", ref.compareAndSet(ref.getReference(), "B", stamp, stamp+1));
        }).start();

        sleep(0.5);
        new Thread(() -> {
//            log.debug("change B->A {}", ref.compareAndSet(ref.get(), "A"));
            int stamp = ref.getStamp();
            log.debug("{}", stamp);
            log.debug("change B->A {}", ref.compareAndSet(ref.getReference(), "A", stamp, stamp+1));
        }).start();

    }
}
