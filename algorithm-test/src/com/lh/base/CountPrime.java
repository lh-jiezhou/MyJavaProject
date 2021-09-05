package com.lh.base;

import java.util.Arrays;

/**
 * 输出 n 以内的质数
 *      厄拉多塞筛法
 *
 */
public class CountPrime {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(prime(n));
    }
    static int prime(int n){
        boolean[] isPrim = new boolean[n];

        Arrays.fill(isPrim, true);

        for (int i = 2; i*i < n; i++) {
            if (isPrim[i]){
                for (int j = i*i; j < n; j+=i) {
                    isPrim[j] = false;
                }
            }
        }

        int count = 0;
        for(int i=2; i<n; i++){
            if (isPrim[i]){
                count++;
            }
        }

        return count;
    }
}
