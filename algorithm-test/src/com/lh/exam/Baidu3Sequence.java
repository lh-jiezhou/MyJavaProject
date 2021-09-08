package com.lh.exam;

import java.util.*;

/**
 * 长度为 n 字符串 求刚好包括 k 种字符 的子序列 个数
 * (子序列中字符顺序不能改变)
 *
 * input: 6 5
 *        eecbad
 * output: 3
 *  (eecbad, ecbad, ecbad)
 */
public class Baidu3Sequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();

        List<String> res = combination(str);

        int count = 0;
        for (int i = 0; i < res.size(); i++) {
            if(isNums(res.get(i), k)){
                count++;
            }
        }
        int result = (int) (count%(Math.pow(10,9)+7));
        System.out.println(result);
    }


    /**
     * 是否有 k 种字母
     * @param str
     * @param k
     * @return
     */
    public static boolean isNums(String str, int k){
        if(str.length() < k){
            return false;
        }
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        return set.size() == k;
    }

    /**
     * 子序列(组合)
     */
    public static List<String> combination(String str) {

        List<String> res = new LinkedList<>();
        int all = str.length();
        int nbit = 1 << all;
        for (int i = 0; i < nbit; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < all; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(str.charAt(j));
                }
            }
            res.add(sb.toString());
        }
        return res;
    }


}
