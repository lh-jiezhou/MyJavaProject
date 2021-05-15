package com.lh.test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Test2 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int procee(int[] scores, int[] cards) {
        // 贪心
        int index = -1;
        int res = scores[0];
        int k = 0; // 已经到的位置
        int count = 0; // 已使用的卡片张数
        while(count < cards.length){ //
            int curMax = -1;
            for(int i=0; i<cards.length && cards[i] != 0; i++){
                // 非零卡片
                int flag = k+cards[i]; // 可达的位置
                if(flag >= scores.length) return res; // 到达终点 提前退出
                if(scores[flag] > curMax){
                    curMax = scores[flag];
                    index = i; // 最终选取的卡片
                }
            }
            k += cards[index]; // 前进几步
            cards[index] = 0; // 已使用的卡片
            count++; // 已使用的卡片张数
            res += curMax; // 贪心 当前最大值
        }
        return res;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _scores_size = 0;
        _scores_size = Integer.parseInt(in.nextLine().trim());
        int[] _scores = new int[_scores_size];
        int _scores_item;
        for(int _scores_i = 0; _scores_i < _scores_size; _scores_i++) {
            _scores_item = Integer.parseInt(in.nextLine().trim());
            _scores[_scores_i] = _scores_item;
        }

        int _cards_size = 0;
        _cards_size = Integer.parseInt(in.nextLine().trim());
        int[] _cards = new int[_cards_size];
        int _cards_item;
        for(int _cards_i = 0; _cards_i < _cards_size; _cards_i++) {
            _cards_item = Integer.parseInt(in.nextLine().trim());
            _cards[_cards_i] = _cards_item;
        }

        res = procee(_scores, _cards);
        System.out.println(String.valueOf(res));

    }
}
