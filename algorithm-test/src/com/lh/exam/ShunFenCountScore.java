package com.lh.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 顺丰笔试 第一题 2021.8.30
 *
 *
 */
public class ShunFenCountScore {

    static int maxScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] para = new int[5];

        for (int i = 0; i < 5; i++) {
            para[i] = scanner.nextInt();
        }
        int n = scanner.nextInt();
        int[][] scores = new int[n][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                scores[i][j] = scanner.nextInt();
            }
        }

        List<Integer> result = countScore(para, scores);
        System.out.println(maxScore);
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }



    }

    static List<Integer> countScore(int[] para, int[][] scores){
        int n = scores.length;
        // 存每个编号对应的 分数
        int[] res = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                sum += para[j] * scores[i][j];
            }
            res[i] = sum;
            sum = 0;
        }

        for (int r: res) {
            maxScore = Math.max(r, maxScore);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(res[i] == maxScore){
                result.add(i+1);
            }
        }
        return result;
    }

}
