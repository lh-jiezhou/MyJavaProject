package com.lh.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * LeetCode54 螺旋矩阵
 *      顶点四坐标
 */
public class SpiralOrder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }

        List<Integer> res = SpiralOrder.spiralOrder(nums);

        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int top = 0, left = 0;
        int bottom = matrix.length-1, right = matrix[0].length-1;
        while(top <= bottom && left <= right){
            // 上边界和右边界
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top+1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            // 下边界和左边界 (判断是否已经遍历)
            if(top < bottom && left < right){
                for (int i = right-1; i >= left ; i--) {
                    res.add(matrix[bottom][i]);
                }
                for (int i = bottom-1; i > top ; i--) {
                    res.add(matrix[i][left]);
                }
            }
            top++;
            left++;
            right--;
            bottom--;
        }

        return res;
    }

}
