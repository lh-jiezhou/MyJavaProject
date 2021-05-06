package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * TimeUnit 设置sleep时间
 *  可读性更好
 */
@Slf4j(topic = "c.Test8")
public class Test8 {

    public static void main(String[] args) throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1); // 1秒
        log.debug("end");
    }
}
