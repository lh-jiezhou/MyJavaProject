package com.lh.exam;

import java.util.Scanner;


public class SpiralOutput {
    /**
     * 广联达,第二题--回形遍历
     * 2021年4月21日21:47:36
     * 一个细节导致全部错误 右到左遍历时 j=n-i-2 !!!
     * 输入：4
     * qwef
     * awad
     * s00s
     * asdf
     * 输出：qwefdsfdsasawa
     */
    public static void main(String[] args) {
        SpiralOutput test = new SpiralOutput();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n*n
        String[] temp = new String[n]; // //字符串数组做中间变量
        for (int i = 0; i < n; i++) {
            temp[i] = sc.next(); // 字符
        }
        char[][] p = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = temp[i].charAt(j); // 字符串转字符
            }
        }
        String res = test.decode(p);
        System.out.println(res);

    }

    String decode(char[][] chars) {
        int n = chars.length; // n 长度
        int m = (chars.length + 1) / 2; // m 层
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < m; i++) { // i为层
            for (int j = i; j < n - i; j++) { // 左到右
                if (chars[i][j] == '0') return str.toString();
                str.append(chars[i][j]);
            }
            for (int j = i + 1; j < n - i; j++) { // 上到下
                if (chars[j][n - i - 1] == '0') return str.toString();
                str.append(chars[j][n - i - 1]);
            }
            for (int j = n - i - 2; j > i; j--) { // 右到左 j=n-i-2 !!!
                if (chars[n - i - 1][j] == '0') return str.toString();
                str.append(chars[n - i - 1][j]);
            }
            for (int j = n - i - 1; j > i; j--) { // 下到上
                if (chars[j][i] == '0') return str.toString();
                str.append(chars[j][i]);
            }
        }
        return str.toString();
    }
}


