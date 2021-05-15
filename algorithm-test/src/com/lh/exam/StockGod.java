package com.lh.exam;

import java.util.Scanner;

/**
 *  赛码：股神
 *      注意周期性
 *      考虑下降的是第几天 再统计
 */
public class StockGod {

    public static void main(String[] args) {
        StockGod test = new StockGod();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int date = sc.nextInt();
            int res = test.countStock(date);
            System.out.println(res);
        }
    }

    int countStock(int date) {
        // 统计跌的次数; 第几天跌：3,6,10,15
        int down = 0;
        int flag = 3; // 第一次跌
        while (flag <= date) {
            down++;
            flag = ((down + 2) * (down + 3)) / 2; // 下一次会跌的天数
        }
        return date - 2 * down; // 跌的时候 date没涨 所以减两倍down

        // 公式
//        int priceDownNum = (int) (Math.sqrt(2*date + 0.25) - 1.5);
//        return date - 2*priceDownNum;
    }

}




