package com.lh.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * n 皇后 经典问题
 *
 *    char 数组 转 String
 *       char[][] board = new char[n][n];
 *       for(char[] chars: board){
 *          // 用法
 *          list.add(new String(chars));
 *       }
 */
public class Queen {

    static List<List<String>> res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        res = new LinkedList<>();
        char[][] board = new char[n][n];

        // 棋盘初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // 第0行开始
        dfs(board, 0);
        System.out.println("方案数: " + res.size());
        System.out.println(res);
    }

    static void dfs(char[][] board, int row) {
        int n = board.length;
        if (row == n) {
            // 满足, 拼接方案
            List<String> list = new ArrayList<>();
            for(char[] chars: board){
                // 用法
                list.add(new String(chars));
            }
            res.add(list);
            return;
        }

        //  遍历每行
        for (int i = 0; i < n; i++) {
            if(!isValid(board, row, i)){
                continue;
            }
            board[row][i] = 'Q';
            dfs(board, row+1);
            board[row][i] = '.';
        }
    }

    /**
     * 判断位置 （row, i） 可不可以存放 'Q'
     * @param board
     * @param row
     * @param col
     * @return
     */
    private static boolean isValid(char[][] board, int row, int col) {
        // 判断列 (列坐标不变,行增加)
        for (int i= 0; i < row; i++) {
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        // 判断主对角线
        for (int i = row-1, j = col-1; i>=0 && j>=0; i--, j--) {
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        // 判断副对角线
        for (int i = row-1, j = col+1; i>=0 && j<board.length; i--, j++) {
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        // 当前位置（row, col）可以放置
        return true;
    }
}
