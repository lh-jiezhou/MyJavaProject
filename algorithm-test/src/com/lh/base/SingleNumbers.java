package com.lh.base;

/**
 * 1. 一个数字出现了一次，其他都出现了两次
 *      直接异或, 0^X = X; X^X = 0
 *
 *
 * 2. 两个数字出现了一次，其他都出现了两次
 *      分组异或
 *
 * 3. 一个数字出现了一次，其他都出现了三次
 *      按位求和 取余
 *
 */
public class SingleNumbers {

    public static void main(String[] args) {
        int[] a1 = new int[]{1, 2, 1, 2, 3};
        int[] a2 = new int[]{1, 2, 1, 2, 3, 5};
        int[] a3= new int[]{1, 2, 1, 3, 1, 3, 3};

        System.out.println(getSingleNumbers1(a1));
        System.out.println(getSingleNumbers2(a2)[0]+ ", "+getSingleNumbers2(a2)[1]);
        System.out.println(getSingleNumbers3(a3));



    }

    static int getSingleNumbers1(int[] nums){
        // 直接异或;
        int res = 0;
        for(int num: nums) {
            res ^= num;
        }
        return res;
    }

    static int[] getSingleNumbers2(int[] nums){
        // 分组异或;
        int flag = 0;
        for(int num: nums) {
            flag ^= num;
        }
        int m = 1;
        while((flag & m) == 0){
            // 找到第一个不同的位置
            m <<= 1;
        }

        int x = 0, y = 0;
        for (int num: nums){
            if((num & m) == 0){
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    static int getSingleNumbers3(int[] nums){
        // 每一个位求和，再对3 取余 即为出现一次的 数字
        int[] counts = new int[32];
        for(int num: nums){

            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>= 1;
            }
        }

        // 恢复
        int m = 3, res = 0;
        for (int i = 31; i >= 0; i--) {
            res <<= 1;
            res = res | counts[i]%m;
        }
        return res;
    }


}
