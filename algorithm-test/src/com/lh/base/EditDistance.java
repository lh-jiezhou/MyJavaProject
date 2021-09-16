package com.lh.base;

import java.util.Scanner;

/**
 * LeetCode 72 编辑距离 字符串 s k
 *      三种操作: 插入\ 删除\ 替换
 *   求 s 转换 k 最少操作数
 *
 *   动态规划 填表
 */
public class EditDistance {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String k = sc.next();

        int res = minDistance(s, k);
        System.out.println(res);
    }

    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 空串提前返回
        if(n * m == 0){
            return m + n;
        }
        int[][] dp = new int[n+1][m+1];

        // 初始化边界第一行
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m+1; j++) {
            dp[0][j] = j;
        }

        // 计算所有, dp[i][j] 表示: word1 前i位 到 word2 前j位 需要 多少步
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                int left = dp[i-1][j] + 1;
                int down = dp[i][j-1] + 1;
                int left_down = dp[i-1][j-1];

                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    left_down += 1;
                }
                dp[i][j] = Math.min(left_down, Math.min(left, down));
            }
        }
        return dp[n][m];

    }


}
