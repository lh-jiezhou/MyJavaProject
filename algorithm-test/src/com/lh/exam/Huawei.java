package com.lh.exam;

import java.util.*;

class Node{
    public String name;
    public Node out;
    public Node in;
    public Node() {}

    public Node(String name) {
        this.name = name;
    }
}

/**
 * huawei1(): 压缩 IPv6 地址; 8 x 4
 *      1.前导 0; 省略
 *      2.连续 0; 压缩 ::
 *      3.单独块 0; 简写 0
 *
 * huawei2(): 最长链路 [id; in; out] 前驱,后继 (只有一个前驱,可以多个后继)
 *      一个流入; 多个流出
 *
 * huawei3(): 子区间面积和 [i,j] 所有子区间面积和
 *      面积定义：(j-i+1) * (max - min); // max为区间元素最大值; min最小值
 *      需要取模 1000000007
 */

public class Huawei {

    public static void main(String[] args) {
        huawei2();
    }

    private static void huawei2() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String D = sc.nextLine();
        // 存结点
        Map<String, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String temp = sc.nextLine();
            // id; in; out
            String[] arr = temp.split(" ");
            String id = arr[0];
            String in = arr[1];
            String out = arr[2];

            Node node;
            Node inNode;
            Node outNode;

            node = nodeMap.containsKey(id) ? nodeMap.get(id): new Node(id);
            if(!in.equals("null")){
                inNode = nodeMap.containsKey(in) ? nodeMap.get(in): new Node(in);
            } else {
                inNode = null;
            }
            if(!out.equals("null")){
                outNode = nodeMap.containsKey(out) ? nodeMap.get(out): new Node(out);
            } else {
                outNode = null;
            }

            // 存入 map
            if(!nodeMap.containsKey(id) && !id.equals("null")){
                nodeMap.put(id, node);
            }
            if(!nodeMap.containsKey(in) && !in.equals("null")){
                nodeMap.put(in, inNode);
            }
            if(!nodeMap.containsKey(out) && !out.equals("null")){
                nodeMap.put(out, outNode);
            }
            node.in = inNode;
            node.out = outNode;
        }

        Node cur = nodeMap.get(D);
        // 前驱 len1
        int len1 = 0;
        Node pre = cur.in;
        while(pre != null){
            len1++;
            pre = nodeMap.get(pre.name).in;
        }
        // 后继 len2
        int len2 = 0;
        Node after = cur.out;
        while(after != null){
            len2++;
            after = nodeMap.get(after.name).out;
        }
        System.out.println(len1+len2+1);
    }


    private static void huawei3() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        int res = fun3(nums);
        System.out.println(res);
    }

    private static int fun3(int[] nums) {
        int sum = 0;
        int base = 1000000007;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int max = getMax(nums, i, j);
                int min = getMin(nums, i, j);
                sum += ((max - min) * (j - i + 1))%base;
            }
        }
        return sum;
    }

    private static int getMax(int[] nums, int i, int j) {
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            if(nums[k] > max){
                max = nums[k];
            }
        }
        return max;
    }
    private static int getMin(int[] nums, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            if(nums[k] < min){
                min = nums[k];
            }
        }
        return min;
    }


    private static void huawei1() {
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine();

        String res = fun1(ip);
        System.out.println(res);
    }

    public static String fun1(String ip){
        int segSize = 4;
        // 非法
        String[] segments = ip.split(":");
        for(String seg: segments){
            if(seg.length() > segSize){
                return "error";
            }
        }
        // 压缩
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < segments.length; i++) {
            String temp = segments[i];
            // 1.先转换 0000 -> 0
            if(temp.equals("0000")){
                sb.append("0");
            } else {
                // 2.去除前导零
                for (int j = 0; j < segSize; j++) {
                    if(temp.charAt(j) != '0'){
                        sb.append(temp.substring(j, segSize));
                        break;
                    }
                }
            }
            if(i != segments.length - 1){
                sb.append(":");
            }
        }

        // 3.连续 0 块
        String[] sbArr = sb.toString().split(":");
        StringBuilder sbRes = new StringBuilder();

        int end = 0;
        boolean flag = true;
        for (int i = 0; i < sbArr.length; i++) {
            if(sbArr[i].equals("0") && flag){
                end = i + 1;
                while(end < sbArr.length){
                    if(!sbArr[end].equals("0")){
                        break;
                    }
                    end++;
                }
                if(end - i >= 2){
                    flag = false;
                    i = end-1;
                } else {
                    sbRes.append(sbArr[i]);
                }
            } else {
                sbRes.append(sbArr[i]);
            }
            if(i != segments.length - 1){
                sbRes.append(":");
            }
        }
        if(sbArr[sbArr.length-1].equals("0") && end == sbArr.length-1){
            sbRes.append(":");
        }
        return sbRes.toString();
    }
}