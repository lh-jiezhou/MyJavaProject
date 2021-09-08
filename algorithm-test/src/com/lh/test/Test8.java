package com.lh.test;

/**
 * 类执行顺序
 */
public class Test8 {

    public Test8() {
        System.out.println("构造方法");
    }
    {
        System.out.println("代码块");
    }
    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        Test8 te = new Test8();
    }
}
