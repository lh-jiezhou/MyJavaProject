package com.lh.exam;

import java.util.Scanner;

/**
 * n*n 方阵 扩大 k 倍 (上采样)
 */
public class Baidu1Pixel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = n*k;
        int[][] matrix = new int[n][n];
        int[][] res = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {

                res[i][j] = matrix[i/k][j/k];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }

}
