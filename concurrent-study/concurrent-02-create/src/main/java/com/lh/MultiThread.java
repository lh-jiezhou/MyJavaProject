package com.lh;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.MultiThread")
public class MultiThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("running");
                }
            }
        }, "t1").start();

        // 另一种写法 lambda
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("running");
            }
        }, "t2").start();

        // 执行后 t1、t2 交替运行

        // 单核测试 会导致其他程序运行不了




    }
}
