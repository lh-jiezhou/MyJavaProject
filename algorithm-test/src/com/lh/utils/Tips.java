package com.lh.utils;

public class Tips {

    public static void main(String[] args) {
        copyArray();
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
