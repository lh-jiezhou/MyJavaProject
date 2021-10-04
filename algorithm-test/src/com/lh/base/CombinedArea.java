package com.lh.base;

import java.util.*;

/**
 * LeetCode 56. 合并区间
 *      in: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      out: [[1,6],[8,10],[15,18]]
 */
public class CombinedArea {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] nums = new int[N][2];
        for (int i = 0; i < N; i++) {
            nums[i][0] = sc.nextInt();
            nums[i][1] = sc.nextInt();
        }
        int[][] res = merge(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.print("["+res[i][0]+","+res[i][1]+"] " );
        }

    }

    public static int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }
        // 左端点排序
        Arrays.sort(intervals, (int[] o1, int[] o2) -> {return o1[0] - o2[0];});

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // 加入, 再更新
            int left = intervals[i][0];
            int right = intervals[i][1];
            if(merged.size() == 0 || left > merged.get(merged.size() - 1)[1]){
                // 直接加入
                merged.add(new int[]{left, right});
            } else {
                // 合并 (更大的右区间)
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }

}
