package com.lh.base;

/**
 * LeetCode 645
 *      找重复和缺失的数字
 *
 */
public class DupAndMisssing {

    public static void main(String[] args) {
        int[] a = new int[]{3, 2, 2, 4};
        int[] res = new int[2];
        res = findErrorNums(a);
        System.out.println(res[0] + " AA " + res[1]);
    }


    public static int[] findErrorNums(int[] nums) {

        // 索引
        int dup = -1;
        int missing = -1;

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                dup = Math.abs(nums[i]);
            } else {
                // 标记为负
                nums[index] *= -1;
            }
        }

        for (int i=0; i<nums.length; i++) {
            // 缺失该索引
            if (nums[i] > 0) {
                missing = i+1;
                break;
            }
        }
        return new int[]{dup, missing};


    }
}
