package com.lh;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TempAlgorithm {

        /*请完成下面这个函数，实现题目要求的功能
        当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
        ******************************开始写代码******************************/
        static int winner(int[] s, int[] t) {
            // 字符串匹配 变种 s子序列包含t
            List<String> subS = new ArrayList(); // 所有s'
            StringBuilder path = new StringBuilder();
            int[] tmp = t.clone();
            int p=0, q=0; // 双指针
            for(int i=0; i<s.length; i++){
                for (int j=0; j<tmp.length; j++){
                    if(s[i] == tmp[j]){
                        p = i; // 左指针
                        tmp[j] = 0; // 标记,已经匹配

                    }
                }
            }
            int local = 5;
            return 5;
        }
        /******************************结束写代码******************************/


        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            int res;

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            String[]values=line.split(",");
            int[]s=new int[values.length];
            for(int i=0;i<values.length;i++){
                s[i] = Integer.parseInt(values[i]);
            }

            line = scanner.nextLine();
            values=line.split(",");
            int[]t=new int[values.length];
            for(int i=0;i<values.length;i++){
                t[i] = Integer.parseInt(values[i]);
            }

            res = winner(s, t);
            System.out.println(String.valueOf(res));

        }

}