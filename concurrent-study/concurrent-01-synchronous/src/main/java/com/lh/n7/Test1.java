package com.lh.n7;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 可变类
 *      SimpleDateFormat
 * 不可变类（线程安全）
 */
@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) {
//        m1();
        m2();
    }

    private static void m2() {
        // 不可变类
        DateTimeFormatter stf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = stf.parse("1951-04-21");
                log.debug("{}", parse);
            }).start();
        }
    }

    private static void m1() {
        // 可变类，可能存在线程安全问题 NumberFormatException
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 加锁可解决，但影响性能
//                synchronized (sdf) {
                    try {
                        log.debug("{}", sdf.parse("1951-04-21"));
                    } catch (Exception e) {
                        log.error("{}", e);
                    }
//                }
            }).start();
        }
    }
}
