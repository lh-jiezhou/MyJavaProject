package com.lh.test;

public class Test13 {

    public static Test13 t1 = new Test13();
    public static Test13 t2 = new Test13();

    {
        System.out.println("构造块");
    }
    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        Test13 m = new Test13();
    }


}