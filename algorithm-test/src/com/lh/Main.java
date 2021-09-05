package com.lh;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 3};
        System.out.println(pivotIndex(nums));

    }

    public static int pivotIndex(int[] nums) {

        int sum = 0;

        for (int num: nums){
            sum += num;
        }

        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 等于时 为中点
            if(sum - nums[i] == preSum){
                return i;
            } else {
                // 更新 前后总和
                preSum += nums[i];
                sum -= nums[i];
            }
        }
        return -1;

    }



}