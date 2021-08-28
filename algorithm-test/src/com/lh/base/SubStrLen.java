package com.lh.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *  定长子串
 *      可以不连续，但要保持顺序
 *   input: str 字符串, k 子串长度
 *   output: 所有子串
 *
 *   input: ebfc
 *          2
 *   output:
 *         ["eb", "ef", "ec", "bf", "bc", "fc"]
 *
 *   求出组合数，再按长度筛选
 */
public class SubStrLen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int k = scanner.nextInt();

        List<String> res = allCombination(str);

        List<String> lenK = new LinkedList<>();
        for (int i = 0; i < res.size(); i++) {
            if(res.get(i).length() == k){
                lenK.add(res.get(i));
            }
        }
        System.out.println(lenK);

    }

    static List<String> allCombination(String str){
        // 组合数，每个位置选，或 不选 （0,1） 二进制
        List<String> res = new LinkedList<>();

        // 所有组合 2^len
        int nbit = 1<<str.length();
        for(int i=0; i<nbit; i++){
            StringBuilder temp = new StringBuilder();
            for(int j=0; j<str.length(); j++){
                if((i & (1<<j)) != 0){
                    // 同时为1时拼接，（i 需要 j）
                    temp.append(str.charAt(j));
                }
            }
            res.add(temp.toString());
        }
        return res;
    }
}
