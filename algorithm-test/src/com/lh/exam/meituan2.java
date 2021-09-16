package com.lh.exam;

import java.util.Scanner;

public class meituan2 {

    private static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        // 邻接矩阵 false
        boolean[][] matrix = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            matrix[u-1][v-1] = true;
            matrix[v-1][u-1] = true;
        }
        // 计数直连
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == true){
                    count++;
                }
            }
            counts[i] = count;
        }

        // 原始与交换后的对应
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i+1;
        }
        for (int i = 0; i < q; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            swap(index, x-1, y-1);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(counts[index[i]-1]+" ");
        }
    }

    public static void swap(int[] nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
