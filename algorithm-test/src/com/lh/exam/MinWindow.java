package com.lh.exam;

import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LeetCode 76. 最小覆盖子串 （困难）
 *      经典滑动窗口
 */
public class MinWindow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        String res = MinWindow.minWindow(s, t);

        System.out.println(res);
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        // 初始化 需求
        for (int i = 0; i < t.length() ; i++) {
            char key = t.charAt(i);
            need.put(key, need.getOrDefault(key, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        // 记录 起始位置
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()){
            char c = s.charAt(right);
            // 扩大窗口
            right++;
            // 数据更新
            if(need.containsKey(c)){
                // window中记录个数
                window.put(c, window.getOrDefault(c, 0)+1);
                if(window.get(c).equals(need.get(c))){
                    // 字母个数满足
                    valid++;
                }
            }
            // 窗口是否收缩
            while(valid == need.size()){
                // 全部满足覆盖时
                if(right - left < len){
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                // 移出窗口
                left++;

                // 数据更新
                if(need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        // 相等时 再减去 就 变少了
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0)-1);
                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }
}
