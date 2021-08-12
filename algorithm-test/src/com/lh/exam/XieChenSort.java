package com.lh.exam;

import java.util.*;

/**
 * 查询满足区间的记录
 * 携程2019届秋招专业笔试-研发方向
 *
 * 有一批订单记录，数据有订单号，入店时间，离店时间；
 * 输入一个时间值A，需要在这批记录中找到符合入离店时间范围（A大于等于入店时间，并且A小于等于离店时间）内的所有记录。
 * 单次查询时间复杂度控制在O(logN)
 */
public class XieChenSort {

    public static void main(String[] args) {
        /**
         * 入店时间排序
         */
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[][] hotels = new int[num][3];
        int time = sc.nextInt();
        List<Integer> res = new LinkedList<>();

        for (int i = 0; i < num; i++) {
            hotels[i][0] = sc.nextInt();
            hotels[i][1] = sc.nextInt();
            hotels[i][2] = sc.nextInt();
        }

        Arrays.sort(hotels,new Comparator<int[]>() {
            // 先比较入店 再比较出店 最后比较编号
            @Override
            public int compare(int[] a, int[] b) {
                if(a[1] != b[1]){
                    return a[1] - b[1];
                } else if(a[2] != b[2]){
                    return a[2] - b[2];
                } else {
                    return a[0] - b[0];
                }
            }
        });

        for (int i = 0; i < num; i++) {
            // 开始时间 晚于 time
            if(time < hotels[i][1]) {
                continue;
            } else {
                // 在区间
                if(time <= hotels[i][2]){
                    res.add(hotels[i][0]);
                }
            }
        }
        if(res.size() == 0){
            System.out.println("null");
            return;
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        Arrays.sort(result);
        for (int a: result){
            System.out.println(a);
        }

    }
}
