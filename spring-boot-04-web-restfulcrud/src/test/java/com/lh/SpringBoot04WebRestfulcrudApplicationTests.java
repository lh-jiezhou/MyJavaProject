package com.lh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot04WebRestfulcrudApplicationTests {

    @Test
    void contextLoads() {
        // Java变量名: 数字、字符、汉字、美元符$、人民币￥、下划线_; 不能以数字开头
        int ￥s = 2;
        String 好s = "好家伙";
        System.out.println(￥s);
        System.out.println(好s);
    }



}
