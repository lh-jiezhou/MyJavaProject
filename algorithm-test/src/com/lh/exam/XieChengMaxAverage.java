package com.lh.exam;

import java.util.Scanner;

/**
 * https://mp.weixin.qq.com/s/YE7irkkY8WProUJzU6GEYw
 * 错峰出行：求 长度大于k 的最大平均数区间
 *     输出区间
 */
public class XieChengMaxAverage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // 前 i 项和
        int[] preSum = new int[n+1];
        preSum[0] = 0;
        for (int i = 1; i < n+1; i++) {
            preSum[i] += preSum[i-1] + arr[i-1];
        }
        long sum = 0;
        int l = 0, r = 0;
        for (int i = 0; i <= n-k; i++) {
            for (int j = k+i; j <= n; j++) {
                long temSum = preSum[j] - preSum[i];
                // sum/(r-l) < tmpSum/(j-i)
                if(sum * (j-i) < temSum * (r-l) ||
                        ( sum * (j-i) == temSum * (r-l) && (j-i) > (r-l))){
                    // 平均值和长度都相等时 不更新 默认为靠左边的区间
                    sum = temSum;
                    l = i;
                    r = j;
                }
            }
        }

        System.out.println(l+" "+(r-1));


    }
}
