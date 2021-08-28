package com.lh.base;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 三数之和 为 0
 *      输出 不重复的组合
 *
 *      方法：排序 + 双指针
 *          0. 排序
 *          1. 固定第一个数 nums[i] 遍历到末尾
 *          2. 使用双指针 left = i+1, right = len-1; 向中间收缩
 *          3. 判断 num[i] + num[left] + num[right] 是否等于 0
 *          4. 由于有序,收缩过程中 判断相邻位去重 left == left + 1 ?
 *          5. i 遍历到末尾结束
 */
public class ThreeNums {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        List<List<Integer>> res = threeSum(nums);
        System.out.println(res);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();

        // 0. 排序
        Arrays.sort(nums);
        // 1. 确定第一个元素 i
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                break;
            }
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }

            // 2. 定义双指针
            int left = i+1, right = nums.length - 1;
            // 3. 收缩判断
            while( left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 4. 相邻去重
                    while(left < right && nums[left+1] == nums[left]){
                        left++;
                    }
                    while(left < right && nums[right-1] == nums[right]){
                        right--;
                    }
                    left++;
                    right--;
                }
                if(sum < 0){
                    left++;
                }
                if(sum > 0){
                    right--;
                }
            }
        }
        return res;
    }
}
