package com.lh.base;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 全排列
 *      方法1. dfs 加入字符的方式
 *      方法2. 交换字符的方式
 */
public class Permeate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        List<Character> path = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            path.add(str.charAt(i));
        }
        List<List<Character>> res = new LinkedList<>();

        allPermeate(res, path, 0);

        for (int i = 0; i < res.size(); i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < res.get(i).size(); j++) {
                temp.append(res.get(i).get(j));
            }
            System.out.println(temp);
        }
    }

    static void allPermeate(List<List<Character>> res, List<Character> path, int index) {
        if(index == path.size()){
            // 注意要 new
            res.add(new LinkedList<>(path));
            return;
        }

        for(int i=index; i<path.size(); i++){
            Collections.swap(path, i, index);
            allPermeate(res, path, index+1);
            Collections.swap(path, i, index);
        }
    }
}
