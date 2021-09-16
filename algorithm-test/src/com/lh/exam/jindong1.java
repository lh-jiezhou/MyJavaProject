package com.lh.exam;

import java.util.Scanner;

public class jindong1 {

    private static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        sc.nextLine();

        char[][] keyboard = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                keyboard[i][j] = (char) (s.charAt(j)-33);
            }
        }

        String goal = sc.nextLine();

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(keyboard[i][j] + " ");
//            }
//            System.out.println();
//        }

        int row = 0;
        int col = 0;
        // Long
        int count = 0;
        char[] goalArr = goal.toCharArray();
        for (int i = 0; i < goalArr.length; i++) {
            int[] temp = findLocal(keyboard, goalArr[i]);
            int goalRow = temp[0];
            int goalCol = temp[1];
            count += minTime(row, col, goalRow, goalCol, x, y, z);
            row = goalRow;
            col = goalCol;
        }

        System.out.println(count);
    }

    public static int[] findLocal(char[][] keyboard, char key){
        key = (char) (key-33);
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[0].length; j++) {
                if(key == keyboard[i][j]){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }

    public static int minTime(int row, int col, int goalRow, int goalCol, int x, int y, int z){
        if(row == goalRow && col == goalCol){
            return z;
        } else if(row == goalRow && col != goalCol) {
            int moveCol = Math.abs(goalCol - col);
            return z + x*moveCol;
        } else if(row != goalRow && col == goalCol) {
            int moveRow = Math.abs(goalRow - row);
            return z + x*moveRow;
        } else {
            // 都不等
            int move = Math.abs(goalCol - col) + Math.abs(goalRow - row);
            // 最多转向一次
            int turn = y*1;
            return z + x*move + turn;
        }
    }
}
