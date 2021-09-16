package com.lh.exam;

import java.util.Scanner;

public class meituan1 {

    private static void main() {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        int x = 22;
        // 能被22整除; 基数位和 == 偶数位

        System.out.println(getDivision(num, x));
    }

    public static int getDivision(String num, int x){

        // 大数除法
        int n = num.length();
        int count = 0;

        char[] arr = num.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = i+2; j < n; j++) {
                if(((arr[j] - '0')-48)%2 == 1){
                    continue;
                }
//                long sonNum = Long.parseLong(num.substring(i, j));
//                if(sonNum % x == 0){
//                    count++;
//                }
                String sonSun = num.substring(i, j);
                int odd = 0;
                int even = 0;
                for (int k = 0; k < sonSun.length(); k++) {
                    if(k % 2 == 0){
                        odd += sonSun.charAt(k)-'0';
                    } else {
                        even += sonSun.charAt(k)-'0';
                    }
                }
                if(odd == even){
                    count++;
                }
            }
        }

        return count;
    }
}
