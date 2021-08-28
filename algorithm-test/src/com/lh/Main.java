package com.lh;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Integer a = 1;
        int i = a.intValue();
        String s1 = "helloword";
        String s2 = "olw";

        System.out.println(windowSlide(s1, s2));


    }


    static boolean windowSlide(String s, String t){

        Map<Character, Integer> mapT = new HashMap();
        Map<Character, Integer> map = (Map<Character, Integer>) ((HashMap<Character, Integer>) mapT).clone();
        for(int i=0; i<t.length(); i++){
            char ch = t.charAt(i);
            int count = mapT.getOrDefault(ch, 0) + 1;
            mapT.put(ch, count);
        }

        int lenT = t.length();
        for(int i=0; i<s.length()-lenT; i++){
            // 滑动窗口，获得子串
            String temp = s.substring(i, i+lenT);
            System.out.println(temp);
            // 进行匹配
            for(int j=0; j<temp.length(); j++){
                char ch = temp.charAt(j);
                int count = mapT.getOrDefault(ch, 0) - 1;
                mapT.put(ch, count);
            }
            System.out.println(mapT);
            // 判断匹配 判断 map 中 value 是否全为0
            boolean flag = true;
            for(Integer value: mapT.values()){
                System.out.println(value+" AAA");
                if(value != 0){
                    mapT = new HashMap<>();
                    for(int j=0; j<t.length(); j++){
                        char ch = t.charAt(j);
                        int count = mapT.getOrDefault(ch, 0) + 1;
                        mapT.put(ch, count);
                    }
                    System.out.println(" FFF " + mapT);
                    flag = false;
                    break;
                }


            }
            if(flag){
                return true;
            }



        }
        // 没有匹配
        return false;
    }



//
//    private static void aqiyi1() {
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        String[] sArr = str.split(",");
//
//        int[] nums = new int[sArr.length];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = Integer.parseInt(sArr[i]);
//        }
//        if(nums.length == 1){
//            System.out.println(nums[0]);
//        }
//        int max = Integer.MIN_VALUE;
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j <= i; j++) {
//                int gap = Math.abs(nums[j] - nums[i]);
//                max = Math.max(gap, max);
//            }
//
//        }
//        System.out.println(max);
//    }
//
//
//    private static void tttt() {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//
//        String[] local = new String[n];
//        for(int i=0; i<n; i++){
//            local[i] = scanner.nextLine();
//        }
//        int goal = 0;
//        char[][] grid = new char[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                grid[i][j] = local[i].charAt(j);
//                if(grid[i][j] == '.'){
//                    goal++;
//                }
//            }
//        }
//
//        int count = 0;
//        count = dfs(grid, goal, 0, 0);
//        System.out.println(count);
//    }
//
//    static int dfs(char[][] grid, int goal, int r, int c){
//        System.out.println(goal+"  AAA ");
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid.length; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("========");
//
//        // 1. base case
//        if(goal == 0 ){
//            // 最后一行
//            if(r == grid.length-1){
//                return 1;
//            } else {
//                goal++;
//                return 0;
//            }
//        }
//        if(!inArea(grid, r, c)){
//            goal++;
//            return 0;
//        }
//        if(grid[r][c] != '.'){
//            goal++;
//            return 0;
//        }
//        // 标记
//        grid[r][c] = '#';
//        goal--;
//
//        // 2. 访问相邻结点
//        return dfs(grid, goal, r-1, c) + dfs(grid, goal, r+1, c)
//                + dfs(grid, goal, r, c-1) + dfs(grid, goal, r, c+1);
//
//    }
//
//    static boolean inArea(char[][] grid, int r, int c){
//        return r>=0 && r<grid.length
//                && c>=0 && c<grid[0].length;
//    }
//
//
//
//    private static void xiaohongshu1() {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        // 奇数
//        if (n == 0 || n % 2 == 1) {
//            System.out.println(-1);
//            return;
//        }
//        // 交替输入处理
//        scanner.nextLine();
//        String[] input = new String[n];
//        for (int i = 0; i < n; i++) {
//            input[i] = scanner.nextLine();
//        }
//
//        int white = 0, black = 0;
//        for (String s : input) {
//            if (s.length() == 1) {
//                if (s.charAt(0) == '1') {
//                    white++;
//                } else {
//                    black++;
//                }
//            }
//        }
//        // 已经超过一半
//        if (white > n / 2 || black > n / 2) {
//            System.out.println(-1);
//            return;
//        }
//        int[][] powers = new int[n - black - white][2];
//        int index = 0;
//        for (int i = 0; i < input.length; i++) {
//            if (input[i].length() != 1) {
//                powers[index][0] = input[i].charAt(2) - '0';
//                powers[index][1] = input[i].charAt(4) - '0';
//                index++;
//            }
//        }
//        int needWhite = n / 2 - white;
//        int needBlack = n / 2 - black;
//
//        int res = minPower(needWhite, needBlack, powers);
//        System.out.println(res);
//    }
//
//    public static int minPower(int a, int b, int[][] powers) {
//        int power = 0;
//        // 少的先选
//        if(a < b){
//            int[][] gapA = new int[powers.length][2];
//            for (int i = 0; i < gapA.length; i++) {
//                gapA[i][0] = powers[i][1] - powers[i][0];
//                gapA[i][1] = i;
//            }
//            Arrays.sort(gapA, (int[] p, int[] q) -> q[0] - p[0] );
//            for (int i = 0; i < a; i++) {
//                power += powers[gapA[i][1]][0];
//            }
//            for (int i = a; i < powers.length; i++) {
//                power += powers[gapA[i][1]][1];
//            }
//            return power;
//        } else {
//            int[][] gapB = new int[powers.length][2];
//            for (int i = 0; i < gapB.length; i++) {
//                gapB[i][0] = powers[i][0] - powers[i][1];
//                gapB[i][1] = i;
//            }
//            Arrays.sort(gapB, (int[] p, int[] q) -> q[0] - p[0] );
//            for (int i = 0; i < b; i++) {
//                power += powers[gapB[i][1]][1];
//            }
//            for (int i = b; i < powers.length; i++) {
//                power += powers[gapB[i][1]][0];
//            }
//            return power;
//        }
//    }
//
//
//
//
//    public static int calc(int a, int b) {
//        if (a > 10) {
//            a = 10;
//        }
//        int result = a + b;
//        result = result * a;
//        return result;
//    }
//
//    private static void testSize() {
//        List<Integer> t1 = new ArrayList<>(10);
//        List<Integer> t2 = new LinkedList<>();
//        System.out.println((t1.size() == 0) + " Arr " + t1.isEmpty());
//        System.out.println((t2.size() == 0) + " Link " + t2.isEmpty());
//    }
//
//    public void swap(int[] nums, int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
//}
}