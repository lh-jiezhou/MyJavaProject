package com.lh.exam;

import java.util.Scanner;

/**
 * 输入一个整数 输出 不大于该整数且只包含1，2，3的最大数
 *      例子: 2 (两组数据)
 *      3244 -> 3233
 *      2201 -> 2133
 */
public class Baidu2PerfectNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n-- > 0){
            String str = sc.next();
            System.out.println(perfectNum(str));
        }
    }

    public static long perfectNum(String str){
        char[] arr = str.toCharArray();
        int j = 0;
        while(j <arr.length){
            if (arr[j] > '3'){
                // break; 后续全置为3
                break;
            } else if(arr[j] == '0'){
                if(j == 0){
                    // 起始为0
                    j++;
                    break;
                }
                // 向前借一位
                arr[j] = '9';
                j--;
                arr[j]--;
            } else {
                // 1, 2, 3
                j++;
            }
        }
        // 后续全置为3
        while(j < arr.length){
            arr[j++] = '3';
        }
        return Long.parseLong(new String(arr));
    }





    public static String Hh(String str){

        if(check(str)){
            return str;
        }

        int len=str.length();
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='0'){
                int k=i-1;
                while(k>=0&&str.charAt(k)=='1'){
                    k--;
                }

                if(k>=0){
                    int tmp=sb.charAt(k)-'0';
                    sb.delete(k,i);
                    sb.append(tmp-1);
                    k++;
                }
                else{
                    sb=new StringBuilder();
                    k=1;
                }

                while(k<len){
                    sb.append(3);
                    k++;
                }
                return sb.toString();
            }
            else if(str.charAt(i)>'3'){
                int k=i;
                while(k<str.length()){
                    sb.append(3);
                    k++;
                }
                return sb.toString();
            }
            // 1, 2, 3
            else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }


    public static boolean check(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=1||str.charAt(i)!='2'||str.charAt(i)!='3'){
                return false;
            }
        }
        return true;
    }
}
