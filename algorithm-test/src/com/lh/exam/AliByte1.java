package com.lh.exam;

import java.util.Scanner;

/**
 * 阿里笔试 1
 *      两个只包含 0, 1 的字符串
 *     字符串 A 变成字符串 B 最少需要几次
 *
 */
public class AliByte1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String a = sc.nextLine();
        String b = sc.nextLine();

        System.out.println(getCnt(a, b));


    }

    public static int getCnt(String A, String B){
        int n = A.length();
        int count01 = 0;
        int count00 = 0;

        if(A.charAt(0) != B.charAt(0)){
            count01++;
        } else {
            count00++;
        }

        for (int i = 1; i < n; i++) {
            if(A.charAt(i) == B.charAt(i)){
                if(A.charAt(i-1) != B.charAt(i-1)){
                    count00++;
                }
            } else {
                if(A.charAt(i-1) == B.charAt(i-1)){
                    count01++;
                }
            }
        }

        return Math.min(count01, count00+1);
    }


    private static void test1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String a = sc.nextLine();
        String b = sc.nextLine();

        int count = 0;
        if(a.charAt(0) != b.charAt(0)){
            count++;
        }
        for (int i = 1; i < n; i++) {
            if(a.charAt(i) != b.charAt(i) && a.charAt(i-1) == b.charAt(i-1)){
                count++;
            }
        }
        System.out.println(count);
    }

}
