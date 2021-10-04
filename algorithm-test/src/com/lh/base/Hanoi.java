package com.lh.base;

import java.util.Scanner;

/**
 * 汉诺塔 Hanoi
 *      需要移动 2^x - 1 次
 *
 * 设移动盘子数为n，为了将这n个盘子从A杆移动到C杆，可以做以下三步：
 * (1)以C盘为中介，从A杆将1至n-1号盘移至B杆；
 * (2)将A杆中剩下的第n号盘移至C杆；
 * (3)以A杆为中介；从B杆将1至n-1号盘移至C杆。
 *
 *   实际操作中，只有第二步可直接完成，而第一、三步又成为移动的新问题。以上操作的实质是把移动n个盘子的
 * 问题转化为移动n-1个盘
 *
 */
public class Hanoi {


    /**
     * count = 2^x-1
     */
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        solve(n,1,2,3);
        System.out.println("移动次数: "+count);
    }


    /**
     * p1为初始盘，p3为目标盘
     */
    public static void solve(int n,int p1,int p2,int p3){
        if(n==1){
            System.out.println("从"+p1+"移动到"+p3);
        }
        else{
            solve(n-1,p1,p3,p2);
            System.out.println("从"+p1+"移动到"+p3);
            solve(n-1,p2,p1,p3);
        }
        count++;
    }


}
