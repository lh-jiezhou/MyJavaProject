package com.lh.exam;

import java.util.*;

/**
 * 求 定长子串 字典序 最大
 *     输入第一行 n, k 表 字符串长度，子串长度
 *     输入第二行 str
 *     输出 字典序最大的串
 *  input: 4 2
 *         ebfc
 *  output: fc
 *  
 *  input: 10 7
 *         yicfihpfbz
 *  output: yiipfbz
 */
public class TencentHH {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String str = scanner.next();

        //
        System.out.println(subMax(str, k));
    }

    /**
     *  先选最后 k 位, 在 [0, str.len - k] 的范围内 找大于 第 k 位的交换
     * @param str
     * @param k
     * @return
     */
    static String subMax(String str, int k) {
        char[] res = new char[k];
        // 已选序列的最后一个位置
        int minLocal = 0;

        int index = 0;
        int start = str.length() - k;
        for (int i = start; i < str.length(); i++) {
            res[index] = str.charAt(i);
            index++;
        }
        // 替换
        for (int i = 0; i < res.length; i++) {
            // j 在 已选最后一个位置 与 str.length() - k 之间
            int j = minLocal;
            while (j < start) {
                char temp = str.charAt(j);
                if( temp > res[i]){
                    res[i] = temp;
                    minLocal = j+1;
                    break;
                } else if(temp == res[i]) {
                    minLocal = j;
                    break;
                } else {
                    j++;
                }
            }
            // 全不满足，
            if(j == start){
                break;
            }
        }

        StringBuilder resStr = new StringBuilder();
        for(char c : res){
            resStr.append(c);
        }
        return resStr.toString();
    }
}
