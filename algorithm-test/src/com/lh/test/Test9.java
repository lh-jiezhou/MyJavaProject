package com.lh.test;

/**
 * 二维数组
 * 每一维长度不一定相同
 * （第一维长度必须指定）
 */
public class Test9 {

    public static void main(String[] args) {

        // 指定长度
        int[][] a = new int[3][];
        a[0] = new int[2];
        a[1] = new int[3];
        a[2] = new int[1];

        // 直接初始化
        int[][] c = new int[][]{{1, 2, 3}, {4}, {5, 6, 7, 8}};
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c[i].length; ++j) {
                System.out.print(c[i][j] + " ");

            }
            System.out.println();
        }

    }

}
