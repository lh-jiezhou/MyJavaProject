package com.lh.exam;

import java.util.Date;

/**
 * 阿里菜鸟网络笔试
 *      不能使用IDE，不要求能直接在IDE运行，所以语法细节上错误没关系
 */
public class CainiaoLogisticeTest {

    public static void main(String[] args) {
        int[] nums = new int[]{1,-3,4,4,-6,8};
        System.out.println(calcMaxSum(nums));

        int[] nums2 = new int[]{-6,-3,1,4,4,8};
        int item=4;
        System.out.println(findSecondBiggerSearch(nums2, item));

    }

    /**
     * 小车位按每小时5元计价，每天最高累计60元。
     * 货车按每小时10元计价，每天最高累计120元。
     * 需要考虑停多天的情况
     * 日期相关的函数不熟悉的话允许伪代码
     *
     * @param parkIn  车辆入车库时间
     * @param parkOut 车辆出车库时间
     * @param carType trunk代表货车位, car代表小车位
     * @return 计费金额
     */
//    public double calc(Date parkIn, Date parkOut, String carType) {
//        // 金额
//        int res = 0;
//        // 时间差 (处理成小时) 并取天花板数ceil 例：4.5 算 5 小时
//        double time = parkOut - parkIn;
//
//        if (carType.equals("trunk")) {
//            // 货车
//            return (time >= 12 ) ? 120 :(10 * time);
//        } else if (carType.equals("car")) {
//            // 小车
//            return time >= 12 ? 60 :5 * time;
//            // if(time >= 12){
//            // return 60;
//            // } else {
//            // return 5*time;
//            //  }
//        } else {
//            // 输入不合法
//            return -1;
//        }
//    }

    /**
     * 一、给定一串数字，numbers={s1,s2,s3...}，求任意满足以下2个条件的序列，输出这个序列的和
     * 要求1：该序列必须连续
     * 要求2：序列里面所有数字加起来最大
     * <p>
     * 二、假如numbers数组长度为N，给出你实现方法的时间复杂度
     * 遍历一遍 时间复杂度 O(n)
     * <p>
     * 例如 输入：{1，-3，4，4，-6，8} 答案可以是{4, 4, -6, 8}，输出：10
     *
     * @param numbers 数字列表
     * @return 最大子序列和
     */
    public static int calcMaxSum(int[] numbers) {
        // 动态规划 dp[i] = max {numbers[i] + dp[i-1], numbers[i]}
        // 数字和最大
        if (numbers.length == 0) {
            return 0;
        }
        int[] dp = new int[numbers.length];
        dp[0] = numbers[0];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < numbers.length; i++) {
            if (dp[i - 1] > 0) {
                // dp[i-1] > 0 有收益才加
                dp[i] = numbers[i] + dp[i - 1];
            } else {
                dp[i] = numbers[i];
            }
        }
        // 返回最大值
        for (int num : dp) {
            System.out.println(" AA " + num);
            max = num > max ? num : max;
        }
        return max;
    }


    /**
     * 一、给定一串已经排序的一串数字，array={s1,s2,s3...}，求比item大的最小元素
     * 二、要求使用二分查找降低程序时间复杂度
     * <p>
     * 例如 输入：{-6，-3，1，4，4，8} item=4 输出：8
     *
     * @param array 已经按照从小到大排序好数组
     * @param item  需要查找的目标元素
     * @return 比item大的最小元素，如果没找到输出-1
     */
    static int findSecondBiggerSearch(int[] array, int item) {

        // 有序，当最后一个元素 <= item 时，不存在比 item 大的
        if (array[array.length - 1] <= item) {
            return -1;
        }
        // 二分法
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > item) {
                // 大于 item 的元素 是当前，或者 左侧
                right = mid;
            } else if (array[mid] < item) {
                // 大于 item 的元素 在右侧
                left = mid + 1;
            } else {
                // 1.等于的时候, 比 item 大的在右侧（直接二分）
                left = mid + 1;

                // 2.等于的时候, 在右侧找第一个不等的 （线性查找） 时间复杂度退化
                // while(mid <= array.length - 1){
                // if(array[mid] != item){
                // return array[mid];
                // }
                // mid++;
                // }
            }
        }
        return array[left];
    }
}
