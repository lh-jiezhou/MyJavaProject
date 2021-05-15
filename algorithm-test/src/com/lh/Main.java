package com.lh;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    static int countProfit(int[] starts, int[] ends, int[] prices){
        // 代码编写
        int[][] matrix = new int[starts.length][3];
        int profit = 0;
        int end = 0;
        for(int i=0; i<starts.length; i++){
            matrix[i][0] = starts[i];
            matrix[i][1] = ends[i];
            matrix[i][2] = prices[i];
        }
        Arrays.sort(matrix[0]);
        for(int i=0; i<starts.length; i++){
            if(matrix[i][0] >= end){
                profit += matrix[i][2];
                end = matrix[i][1];
            }

        }

        return profit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] prices = new int[n];
        for(int i=0; i<n; i++) starts[i] = sc.nextInt();
        for(int i=0; i<n; i++) ends[i] = sc.nextInt();
        for(int i=0; i<n; i++) prices[i] = sc.nextInt();
        int res = countProfit(starts, ends, prices);
        System.out.println(res);
    }
}
