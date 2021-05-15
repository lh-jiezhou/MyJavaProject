package com.lh.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 同步模式-交替输出
 *      park() 与 unpark()
 *  都是以线程自身为单位的 (更简洁)
 *
 *  三个线程： 输出 abcabcabcabcabc
 */
@Slf4j(topic = "c.Test31")
public class Test31 {

    public static void main(String[] args) {

    }

}

class ParkUnpark{

}


