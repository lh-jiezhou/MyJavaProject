package com.lh.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 顺丰笔试 第二题 2021.8.30
 *
 *
 */
public class ShunFenTravel {

    static int aMax = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();

        // 不舒适度
        int[][] weights = new int[m][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                weights[i][j] = scanner.nextInt();
            }
        }
        // 起点, 终点
        int[][] goals = new int[q][2];
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < 2; j++) {
                goals[i][j] = scanner.nextInt();
            }
        }

        // 权重为 1 最短路径
        // 路径中不舒适度 最大值
        Arrays.sort(weights, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });
        // 第q次
        for (int i = 0; i < q; i++) {
            if (goals[i][0] == goals[i][1]) {
                System.out.println(0);
                continue;
            }
            if (goals[i][0] < goals[i][1]) {
                dfs(goals[i][0], goals[i][1], weights, 0);
            } else if (goals[i][0] > goals[i][1]) {
                dfs(goals[i][1], goals[i][0], weights, 0);
            }
            System.out.println(aMax);
            aMax = 0;
        }


    }

    static void dfs(int start, int end, int[][] weights, int index) {
        // 防止越界
        if (index >= weights.length) {
            aMax = 0;
            return;
        }
        if (weights[index][1] == end) {
            aMax = Math.max(weights[index][2], aMax);
            return;
        }

        if (start == weights[index][0]) {
            aMax = Math.max(weights[index][2], aMax);
            dfs(weights[index][1], end, weights, index + 1);
        } else {
            dfs(start, end, weights, index + 1);
        }
    }

}
