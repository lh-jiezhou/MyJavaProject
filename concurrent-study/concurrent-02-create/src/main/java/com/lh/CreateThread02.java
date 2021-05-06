package com.lh;

import lombok.extern.slf4j.Slf4j;

/**
 *  Runnable 配合 Thread;
 *       线程与任务 分离(推荐使用)
 *  lambda简化写法
 *       当一个接口中只有一个方法时 可使用 @FunctionalInterface注解
 *       @FunctionalInterface 注解修饰的接口 可以用lambda简化写法
 */
@Slf4j(topic = "c.Test")
public class CreateThread02 {

    // 方法二: 使用 Runnable 配合 Thread;
    // 线程与任务 分离(推荐使用)
    public static void main(String[] args) {

        // 任务对象
        Runnable runnable = new Runnable() { // Runnable接口
            @Override
            public void run() {
                // 要执行的任务
                log.debug("Runnable running");
            }
        };

//        Runnable runnable = () ->{ log.debug("Runnable running"); };

        // 线程对象
        Thread t = new Thread(runnable, "t2"); // 构造方法传值
        t.start(); // 启动线程

        log.debug("main-running"); // 主线程中
    }

}
