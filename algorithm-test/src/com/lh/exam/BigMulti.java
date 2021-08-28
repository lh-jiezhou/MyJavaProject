package com.lh.exam;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 大数问题 哔哩哔哩2020校园招聘测试笔试卷（一）
 *      大数乘法; 大数指超过int64_t 可承载范围的数字
 *
 *      1. 模拟法(最后统一进位)
 *      2. 分治法 Karatsuba
 *      3. BigInteger 类，a.multiply(b)
 *
 *      Integer.parseInt(String.valueOf(a.charAt(i)));
 *         char强转 int 会输出 ASCII 码 可以 减去 48 已还原数值
 *
 */
public class BigMulti {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n1 = sc.next();
        String n2 = sc.next();

        String res = BigMulti.bigMulti(n1, n2);
        System.out.println(res);
    }


    private static String bigMulti(String a, String b) {

        StringBuilder res = new StringBuilder();
        int[] result = new int[a.length() + b.length()];

        // 相乘, 先不考虑进位
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                // result[0] 刚好空出来 给后续进位 (直接强转为 ASCII码 还需减去48)
                result[i + j + 1] +=  ((int)a.charAt(i)-48) * ((int)b.charAt(j)-48);
            }
        }
        // 处理进位
        for (int i = result.length - 1; i > 0; i--) {
            if(result[i] > 10){
                result[i-1] += result[i]/10;
                result[i] %= 10;
            }
        }
        if(result[0] != 0) {
            res.append(result[0]);
        }
        for (int i = 1; i < result.length; i++) {
            res.append(result[i]);
        }
        return res.toString();
    }


    /**
     * BigInteger 类
     */
    public static void f(){
        Scanner sc = new Scanner(System.in);
        BigInteger num1 = new BigInteger(sc.nextLine());
        BigInteger num2 = new BigInteger(sc.nextLine());
        BigInteger result = num1.multiply(num2);
        System.out.println(result);
    }
}
