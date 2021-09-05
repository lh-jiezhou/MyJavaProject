package com.lh.test;

/**
 * 类加载机制
 */

public class Test6 {
    static int count = 0;

    static {
        count++;
        System.out.println("A");
    }

    public Test6() {
        System.out.println("B");
    }
}

class ClassB {
    static {
        Test6 t2;
        System.out.println("C");
    }

    public static void main(String[] args) {
        Class c1;
        Class c2;
        Class c3;
        try {
            // 获取类的三种方式
            c1 = Test6.class;
            c2 = Class.forName("com.lh.test.Test6");
            Test6 a = new Test6();
            c3 = a.getClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (c2 == c1 && c1 == c3) {
            System.out.println("D");
        } else {
            System.out.println("E");
        }
        System.out.println(Test6.count);
    }
}
