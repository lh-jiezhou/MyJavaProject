package com.lh;
import javax.sound.midi.Soundbank;
import java.util.*;


public class Temp {

    Map<Integer, Character> map = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        String[] sArr = str.split(" ");

        int[] nums = new int[sArr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(sArr[i]);
        }

        System.out.println(minPaper(nums));

        Random random = new Random();
    }

    static int minPaper(int[] nums){
        // 年龄最小
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < min){
                min = nums[i];
                index = i;
            }
        }
        // 最小值的位置 纸张数置为 1
        int[] counts = new int[nums.length];
        counts[index] = 1;
        for(int i=index-1; i>=0; i--){
            if(nums[i] > nums[i+1]){
                counts[i] = counts[i+1] + 1;
            } else if(nums[i] < counts[i+1]){
                counts[i] = 1;
            } else {
                counts[i] = counts[i+1];
            }
        }
        for (int i = index+1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]){
                counts[i] = counts[i-1] + 1;
            } else if (nums[i] < counts[i-1]){
                counts[i] = 1;
            } else {
                counts[i] = counts[i-1];
            }
        }
        int sum = 0;
        for (int count: counts){
            sum += count;
        }
        return sum;
    }




    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回Sn的第k位字符
     * @param n int整型 Sn的n
     * @param k int整型 需要返回的字符下标位
     * @return char字符型
     */
    public char findKthBit (int n, int k) {
        // write code here
        map.put(1, 'a');
        map.put(2, 'b');
        map.put(3, 'c');
        map.put(4, 'd');
        map.put(5, 'e');
        map.put(6, 'f');
        map.put(7, 'g');
        map.put(8, 'h');
        map.put(9, 'i');
        map.put(10, 'j');
        map.put(11, 'k');
        map.put(12, 'l');
        map.put(13, 'm');
        map.put(14, 'n');
        map.put(15, 'o');
        map.put(16, 'p');
        map.put(17, 'q');
        map.put(18, 'r');
        map.put(19, 's');
        map.put(20, 't');
        map.put(21, 'u');
        map.put(22, 'v');
        map.put(23, 'w');
        map.put(24, 'x');
        map.put(25, 'y');
        map.put(26, 'z');
        String res = getS(n);
        return res.charAt(k-1);
    }

    String getS(int i){
        if(i == 1){
            return "a";
        }
        String li = Character.toString(map.get(i));
        return getS(i-1) + li + reverseInvert(getS(i-1));
    }

    String reverseInvert(String s){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            str.append(map.get(27 - (s.charAt(i) - 'a'+1)));
        }
        return str.reverse().toString();
    }
}