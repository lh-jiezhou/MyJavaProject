package com.lh.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 罗马数字 与 整形相互转换
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 */
public class RomanAndInt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String roman = sc.nextLine();
        int num = sc.nextInt();

        System.out.println(romanToInt(roman));
        System.out.println(intToRoman(num));

    }

    /**
     * 罗马 转 整形
     *      当小值在大值的左边，则减小值，如 IV=5-1=4；
     *      当小值在大值的右边，则加小值，如 VI=5+1=6；
     *      由上可知，右值永远为正，因此最后一位必然为正
     * @param s
     * @return
     */
    public static int romanToInt(String s){
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int value = map.get(c[i]);
            if( i < c.length-1 && value < map.get(c[i+1])) {
                res -= value;
            } else {
                res += value;
            }
        }
        return res;
    }

    /**
     * 整形 转 罗马
     *      范围: 3999
     *      贪心
     * @param num
     * @return
     */
    public static String intToRoman(int num){
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < values.length && num >= 0; i++) {
            // 循环减
            while (num >= values[i]){
                num -= values[i];
                res.append(symbols[i]);
            }
        }
        return res.toString();
    }

}
