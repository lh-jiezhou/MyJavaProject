package com.lh.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 编译看左，运行看右
 *      非静态成员方法看右，其余全看左
 *      age 看左边 声明的类
 */
class Parent{
    int age = 55;

    public int getNum(int a){
        return a+1;
    }
    public static void getA(){
        System.out.println("Parent");
    }

}
class Child extends Parent{
    int age = 23;

    @Override
    public int getNum(int a){
        return a+2;
    }
    public static void getA(){
        System.out.println("Child");
    }
}
public class Test4 {

    public static void main(String[] args) {
        Child c1 = new Child();
        Parent c2 = new Child();

        System.out.println(1+2+"3"+4);
        System.out.println(c1.age+"=="+c2.age);
        // 非静态成员方法 看右边
        System.out.println(c2.getNum(0));
        c2.getA();
    }
}
