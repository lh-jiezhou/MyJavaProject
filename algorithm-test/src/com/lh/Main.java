package com.lh;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Main test = new Main();
        // 输入二维char数组

        int i1 = 3;
        Integer i2 = i1;
        Float f1 = 40.0f; // 编译错误
        double f2 = f1;

        System.out.println(test.devide(6,0));

    }
    int devide(int a, int b) {
       int i = 1;
       try{
           i = a/b;
       } catch (Exception e){
           System.out.println("exception");
       } finally {
           System.out.println("final");
       }
       return i;
    }
}


