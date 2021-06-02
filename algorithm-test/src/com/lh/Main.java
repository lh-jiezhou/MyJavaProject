package com.lh;
import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Main test = new Main();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String paths = sc.nextLine();
        int x = sc.nextInt(); // 起点
        int y = sc.nextInt(); // 终点
        int k = sc.nextInt(); // 最大中转
        int res = 0; // 所有方案; 中转0次(直达) --> 中转k次

        int n; // 车站
        if(str.length() == 1) n = str.charAt(0) - '0';
        else n = 10*(str.charAt(0) - '0') + (str.charAt(1) - '0');

        res = test.getTrainPlans(n, paths, x, y, k);
        System.out.println(res);
    }

    int getTrainPlans(int n, String paths, int x, int y, int k){
        // 拓扑排序 算法
        // 构造数据结构 有向无环图(DAG图)

        int count = 2; // 计数
        return count;
    }


}
