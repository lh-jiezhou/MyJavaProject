package com.lh.test;

import org.junit.jupiter.api.Test;

/**
 * 小 test
 *
 */
public class Test0 {

    @Test
    void t1(){
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(2, 31)-1);
        System.out.println(Integer.MAX_VALUE);



        Integer  a=1;
        Integer c=1;
        Integer b=a;
        a = a++;
        System.out.println(a+b);
        System.out.println(a==c);
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));

    }
}
