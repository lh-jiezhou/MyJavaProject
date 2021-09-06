package com.lh;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[][] nums = new int[][]{{1, 2,3},{4, 5,6},{7,8,9}};
        int[] res =  findDiagonalOrder(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }

    public static int[] findDiagonalOrder(int[][] mat) {
        // 对角线 = m + n - 1
        int m = mat.length;
        int n = mat[0].length;
        int index = m + n - 1;

        List<Integer> res = new ArrayList<>();


        for(int i=1; i<=index; i++){
            List<Integer> temp = new ArrayList<>();

            // 行起点 (0, i-1)
            if(i-1<n){
                for(int j=0, k=i-1; j<m && k>=0; j++, k--){
                    temp.add(mat[j][k]);
                }
            }
            // 列起点 (i-n, n-1)
            else {
                for(int j=i-n, k=n-1; j<m && k>=0; j++, k--){
                    temp.add(mat[j][k]);
                }
            }
            int[] t = new int[temp.size()];
            for(int j=0; j<temp.size(); j++){
                t[j] = temp.get(j);
            }

            // 奇数对角线(左下 -> 右上)
            if(i%2 == 1){
                reverse(t);
            }
            for(int k=0; k<t.length; k++){
                res.add(t[k]);
            }


        }


        System.out.println(res.size());
        int[] result = new int[res.size()];
        for(int i=0; i<res.size(); i++){
            result[i] = res.get(i);
        }
        return result;



    }

    static void reverse(int[] nums){
        for(int i=0; i<nums.length/2; i++){
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
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