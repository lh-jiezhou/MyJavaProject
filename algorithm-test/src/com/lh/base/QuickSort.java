package com.lh.base;

import java.util.Random;
import java.util.Timer;

public class QuickSort {

    static Random random = new Random();

    public static void main(String[] args) {
        int[] nums = new int[]{1,44,31,23,68,2,13,57,19};
        int[] nums2 = new int[10000000];
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = i;
        }

//        long start1 = System.currentTimeMillis();
//        quickSort(nums, 0, nums.length-1);
//        long end1 = System.currentTimeMillis();
//        System.out.println((end1-start1)/1000);

        long start2 = System.currentTimeMillis();
        quickSort2(nums, 0, nums.length-1);
        long end2 = System.currentTimeMillis();
        System.out.println("随机化: "+ (end2-start2)/1000);

        for (int num: nums){
            System.out.print(num + "  ");
        }
    }

    static void quickSort(int[] nums, int left, int right){
        if(left < right){
            int m = partition(nums, left, right);
            quickSort(nums, left, m-1);
            quickSort(nums, m+1, right);
        }
    }

    static int partition(int[] nums, int left, int right){
//        int ran = random.nextInt(right - left) + left;
//        swap(nums, ran, left); // 随机化
        int first = nums[left];
        while(left < right){
            while(left < right && first <= nums[right]) right--;
            swap(nums, left, right);
            while(left < right && nums[left] <= first) left++;
            swap(nums, left, right);
        }
        return left;

    }

    static void quickSort2(int[] nums, int left, int right){
        if(left < right){
            int m = partition2(nums, left, right);
            quickSort2(nums, left, m-1);
            quickSort2(nums, m+1, right);
        }
    }

    static int partition2(int[] nums, int left, int right){
        int ran = random.nextInt(right - left) + left;
        swap(nums, ran, left); // 随机化
        int first = nums[left];
        while(left < right){
            while(left < right && first <= nums[right]) right--;
            swap(nums, left, right);
            while(left < right && nums[left] <= first) left++;
            swap(nums, left, right);
        }
        return left;

    }

    static void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
