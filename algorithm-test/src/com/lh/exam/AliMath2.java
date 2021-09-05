package com.lh.exam;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 阿里笔试 2
 *      找出 符合不等式的 x , y 的组数
 *      https://www.cnblogs.com/xidian-mao/p/15224816.html
 */
public class AliMath2 {


    @Test
    private static void test2() {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();

        List<Long> xList = new ArrayList<>();
        List<Long> yList = new ArrayList<>();

        int res = 0;


        long aStart = (long) Math.sqrt(a);
        for (long i = aStart; i*i <= b; i++) {
            xList.add(i);
        }

        for (long i = 1; i*i*i <= b; i++) {
            if(i*i*i >= a){
                yList.add(i);
            }
        }

        for (int i = 0; i < xList.size(); i++) {
            for (int j = 0; j < yList.size(); j++) {
                long x2 = xList.get(i)*xList.get(i);
                long y3 = yList.get(j)*yList.get(j);
                if(Math.abs(x2-y3) <= c){
                    res++;
                }
            }

        }
        System.out.println(res);
    }

}
