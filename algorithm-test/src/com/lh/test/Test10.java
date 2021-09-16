package com.lh.test;

/**
 * 拆箱，装箱
 * 引用，局部变量
 */
public class Test10 {

    static String a = "6";
    static double b = 1;

    public static void main(String[] args) {
        int c = 8;
        System.out.println(a+b+c);

        int Z = 100;
        Integer A = 100;
        Integer B = 100;

        int X = 1000;
        Integer C = 1000;
        Integer D = 1000;
        // true
        System.out.println(A == Z);
        // true
        System.out.println(A == B);
        // false
        System.out.println(C == D);
        // true
        System.out.println(C == X);


        String ss = "hello";
        char[] aa = new char[]{'a', 'b', 'c'};
        change(ss, aa);
        System.out.println(ss);
        System.out.println(new String(aa));

    }

    public static void change(String str, char[] arr){
        str = "change";
        arr[0] = 'c';
    }


}
