package com.lh.test;

import com.lh.Constants;
import com.lh.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * run() 方法与 start()方法
 * run()在原来main线程上
 */
@Slf4j(topic = "c.Test4")
public class Test4 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
                FileReader.read(Constants.MP4_FULL_PATH);
            }
        };
        String x = "1";
        int y = 1, z = 2;
        System.out.println(x+y+z);

//        t1.run(); // 仍然是在main线程
        t1.start(); // 新线程
        log.debug("do other things...");
    }
}
