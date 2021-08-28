package com.lh.exam;

import java.util.*;

/**
 *
 *题目：有 n 个学生站成一排，每个学生有一个能力值，想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，
 * 使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 *
 * 输入：
 * 第一行：一个整数 n (1 <= n <= 50)，表示学生的个数
 * 第二行： n 个整数，按顺序表示每个学生的能力值 ai（-50<= ai<=50）
 * 第三行：两个整数，k 和 d (1 <= k <= 10, 1 <= d <= 50)
 * 输出：
 * 最大乘积
 *
 * input:
 * 3
 * 7 4 7
 * 2 50
 * output:
 * 49
 */
public class MaxMulti{

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i=1; i<n+1; i++){
            arr[i] = sc.nextInt();
        }
        // 选 k 个；相邻间距不超过 d ( <= )
        int k = sc.nextInt();
        int d = sc.nextInt();

        //求最大乘积的时候，每一步需要求最大正积和最小负积
        //因为如果某学生的能力值为负数，乘以前面求得的最小负积，结果才是最大乘积
        //最后算的数据比较大，需要用long
        // max[k][h]表示 : 当选中了h个学生，并且以第k个学生为结尾，所产生的最大乘积；
        // min[k][h]表示 : 当选中了h个学生，并且以第k个学生为结尾，所产生的最小乘积；
        long[][] max =  new long[n+1][k+1];
        long[][] min =  new long[n+1][k+1];
        long res = Integer.MIN_VALUE;
        for(int last = 1;last<= n;last++){
            //初始化h=1的情况，只选中1个人的情况
            max[last][1] = arr[last];
            min[last][1] = arr[last];
            //h>1的情况，选中人数从2开始
            for(int h = 2;h <= k;h++){
                for(int left = last-1; left>0 && last-left<=d;left--){
                    max[last][h] = Math.max(max[last][h],Math.max(max[left][h-1]*arr[last],min[left][h-1]*arr[last]));
                    min[last][h] = Math.min(min[last][h],Math.min(max[left][h-1]*arr[last],min[left][h-1]*arr[last]));
                }
            }
            res = Math.max(res, max[last][k]);
        }
        System.out.println(res);

    }

}