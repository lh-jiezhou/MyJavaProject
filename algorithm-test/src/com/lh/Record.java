package com.lh;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Record {


    /**
     * 备忘录法 斐波拉契数列
     * @param N
     * @return
     */
    public int fib(int N) {
        // 备忘录全初始化为 0
        int[] memo = new int[N + 1];
        // 进行带备忘录的递归
        return helper(memo, N);
    }

    private int helper(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) return n;
        // 已经计算过，不用再计算了
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     * 9*9乘法表
     */
    public static void formula(){
        int size = 9;
        String blank1 = "     ";
        String blank2 = "    ";
        for(int i=1; i<=size; i++){

            for(int j=1; j<=i; j++){
                if(i*j < 10){
                    System.out.print(j+"*"+i+"="+i*j+blank1);
                } else {
                    System.out.print(j+"*"+i+"="+i*j+blank2);
                }

            }
            System.out.println();
        }
    }


    /**
     * 滑动窗口 求最大变化率
     */
    private static void aiqiyi2() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");

        // input
        String s = sc.nextLine();
        String[] array = s.split(",");
        int[] nums = new int[array.length];
        for (int i = 0; i < nums.length-1; i++) {
            nums[i] = Integer.parseInt(array[i]);
        }
        String[] temp = array[array.length-1].split(":");
        nums[nums.length-1] = Integer.parseInt(temp[0]);
        int size = Integer.parseInt(temp[1]);

        // algorithm
        int[] windows = new int[size];
        double pMax = Integer.MIN_VALUE;


        for (int i = 0; i < size; i++) {
            // 初始化
            windows[i] = nums[i];
        }
        int windowsSum = Record.getSum(windows);
        double preAver = windowsSum/(size*1.0);
        for (int i = size; i < nums.length; i++) {
            int gap = nums[i] - nums[i-size];
            windowsSum += gap;
            double curAver = windowsSum/(size*1.0);
            double curP = curAver/preAver;
            preAver = curAver;
            if(curP > pMax) {
                pMax = curP;
            }
        }
        pMax -= 1;
        pMax *= 100;
        System.out.println(String.format("%.2f", pMax) + "%");
    }

    /**
     * 数组求和
     * @param nums
     * @return
     */
    static int getSum(int[] nums){
        int sum = 0;
        for(int num: nums){
            sum += num;
        }
        return sum;
    }


    /**
     * 0/1背包 填表
     * @param results
     * @param ws
     * @param vs
     * @return
     */
    public static int ks3(int[][] results, int[] ws, int[] vs){
        // 初始化

        for (int m = 0; m < results.length; m++){
            results[m][0] = 0;
        }
        for (int m = 0; m < results[0].length; m++){
            results[0][m] = 0;
        }
        // 开始填表
        for (int m = 1; m < results.length; m++){
            for (int n = 1; n < results[0].length; n++){
                if (n < ws[m]){
                    // 装不进去
                    results[m][n] = results[m-1][n];
                } else {
                    // 容量足够
                    if (results[m-1][n] > results[m-1][n-ws[m]] + vs[m]){
                        // 不装该珠宝，最优价值更大
                        results[m][n] = results[m-1][n];
                    } else {
                        results[m][n] = results[m-1][n-ws[m]] + vs[m];
                    }
                }
            }
        }
        return results[results.length-1][results[0].length-1];
    }

    public static String minArrayCom(String[] strs){
        Arrays.sort(strs, (s1, s2) -> (s1 + s2).compareTo((s2 + s1)));

        StringBuilder temp = new StringBuilder();
        for(String s: strs){
            temp.append(s);
        }
        return temp.toString();
    }


    public static String reverse(String s){
        if (s == null) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        List<String> temp = new ArrayList<>();

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' '){
                temp.add(word.toString());
                word.delete(0, word.length());
            } else {
                word.append(s.charAt(i));
            }
        }
        // 末尾
        temp.add(word.toString());

        for (int i = temp.size()-1; i >= 0; i--) {
            res.append(temp.get(i));
            res.append(' ');
        }

        return res.toString();
    }





    /**
     * 厄拉多塞筛法
     * 范围里素数个数
     * @param m
     * @param n
     * @return
     */
    static int getPrime(int m, int n){
        int count = 0;
        boolean[] b = new boolean[n+1];
        if(n >=3 && m <= 3){
            ++count;
        }

        for(int i=3; i<=n; i+=2){
            if(!b[i]){
                for(int j=3; i*j < n; j+=2){
                    // 所有质数倍数 设置为 true
                    b[i*j] = true;
                }
                // 不在范围内
                if(i<m){
                    continue;
                }
                count++;
            }
        }

        return count;

    }

}
