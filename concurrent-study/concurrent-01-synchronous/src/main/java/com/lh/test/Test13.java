package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式 Two Phase Termination
 *    在一个线程 t1 中如何“优雅”终止线程 t2
 *    “优雅”指的是给 t2 一个料理后事的机会
 */
@Slf4j(topic = "c.Test13")
public class Test13 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(3500);
        tpt.stop();

    }

}

// 两个类
@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {
    // 监控线程
    private Thread monitor;
    // 新增 volatile 实现 p138
    private volatile boolean stop = false; //


    // 启动监控线程
    public void start(){
        monitor = new Thread(() -> {
            while(true){
                Thread current = Thread.currentThread();
//                if(stop){
                if(current.isInterrupted()){ // 标记为true说明被打断
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000); // 情况一: 此行被打断
                    log.debug("执行监控记录"); // 情况二: 此行被打断
                } catch (InterruptedException e) {
                    e.printStackTrace(); // 睡眠中被打断 会重置标记为false
                    // 重新设置打断标记 为true
                    current.interrupt();
                }
            }
        }, "t1");

        monitor.start();
    }

    // 停止监控线程
    public void stop(){
        stop = true;
        monitor.interrupt(); // 打断
    }

}