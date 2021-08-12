package com.lh.utils;

import java.util.Arrays;
import java.util.Comparator;

public class Tips {

    public static void main(String[] args) {
        copyArray();

        int[][] nums=new int[][]{{1,3},{1,2},{4,2},{4,5},{3,7}};
        sortArray(nums);
        for (int[] num: nums){
            System.out.print(num[0]+"  "+num[1]);
            System.out.println();
        }
    }

    public static void sortArray(int[][] nums){
        Arrays.sort(nums,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                //表示第二个数相同就按第一个数排序，不同则比较第二个数
                if(a[1]==b[1]){
                    return a[0]-b[0];
                }else{
                    return a[1]-b[1];
                }
            }
        });
    }

    public static void copyArray(){
        /**
         * 数组赋值操作
         */
        int[] nums = {1, 2, 3};
        int[] n1 = nums; // 不开辟新空间
        int[] n2 = nums.clone(); // 开辟新空间
        System.out.println(nums.hashCode());
        System.out.println(n1.hashCode());
        System.out.println(n2.hashCode());
    }

}
