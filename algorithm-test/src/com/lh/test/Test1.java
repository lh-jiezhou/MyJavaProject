package com.lh.test;

/**
 * 多态
 *  class Zi extends Fu{}
 *  Fu f = new Zi();
 *      A：成员变量：编译和运行都看Fu。
 *      B：静态方法：编译和运行都看Fu。
 *      C：非静态方法：编译看Fu，运行看Zi
 */
public class Test1 {
}

class A {
    public int a = 0;

    public void fun(){
        System.out.println("A");
    }

    public static void getS(){
        System.out.println("staticA");
    }
}

class B extends A {
    public int a = 1;

    @Override
    public void fun() {
        System.out.println("B");
    }

    public static void getS(){
        System.out.println("staticB");
    }

    public static void main(String[] args) {
        A classA = new B();
        System.out.println(classA.a);
        classA.fun();
        classA.getS();
    }
}