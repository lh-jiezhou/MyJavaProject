package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程 Runnable
 * 任务对象 线程对象 分离
 */

@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) {
        // 任务对象
        Runnable r = () -> {log.debug("running");};
        // 线程对象
        Thread t = new Thread(r, "t2");
        // 启动线程
        t.start();
    }


}
