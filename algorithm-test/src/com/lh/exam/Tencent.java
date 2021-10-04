package com.lh.exam;
import java.util.*;

/**
 * fun1.因子
 * fun2.跳跃
 * fun3.新表达式
 */
public class Tencent {

    public static void main(String[] args) {
        tencent3();
    }

    private static void tencent3() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        long res = fun3(str);
        System.out.println(res);
    }

    private static long fun3(String str) {
        // 两个栈
        Stack<Long> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();

        char[] arr = str.toCharArray();
        // 标记 连续数字
        int flag = 0;

        // 入栈
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= '9' && arr[i] >= '0'){
                if(flag == 0 && !numStack.isEmpty()){
                    long a = numStack.pop();
                    long b = arr[i] - '0';
                    numStack.push(a*10 + b);
                } else {
                    // flag == 1 前一个是运算符
                    numStack.push((long) (arr[i] - '0'));
                    flag = 0;
                }

            } else if(arr[i] == '@'){
                // 先计算
                long a = numStack.pop();
                long b = 0;
                int c = 1;
                while(i+c < arr.length && (arr[i+c] <= '9' && arr[i+c] >= '0')){
                    // 连续数字
                    int d = arr[i+c] - '0';
                    b = b*10 + d;
                    c++;
                }
                numStack.push(getAt(a, b));
                i+=(c-1);
                flag = 1;
            } else if(arr[i] == 'x'){
                // 直接入栈
                charStack.push(arr[i]);
                flag = 1;
            } else if(arr[i] == '+'){
                // 判断前一个符号是否为 'x'
                if(!charStack.isEmpty() && charStack.peek() == 'x'){
                    // 取出运算
                    long a = numStack.pop();
                    long b = numStack.pop();
                    numStack.push(a * b);
                    charStack.pop();
                    charStack.push(arr[i]);
                } else {
                    // 直接入栈
                    charStack.push(arr[i]);
                }
                flag = 1;
            } else {
                // 非法字符
                break;
            }
        }
        // 出栈
        while(!charStack.isEmpty()){
            long a = numStack.pop();
            long b = numStack.pop();
            if(charStack.peek() == 'x'){
                numStack.push(a * b);
                charStack.pop();
            } else {
                // '+'
                numStack.push(a + b);
                charStack.pop();
            }
        }

        return numStack.peek();
    }

    private static long getAt(long a, long b){
        return a|(a+b);
    }


    private static void tencent2() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] res = new int[T];
        int index = 0;

        while (index < T) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            res[index] = fun2(nums);
            index++;
        }

        for(int i: res){
            System.out.println(i);
        }
    }


    private static int fun2(int[] a) {

        // dp[i] = a[i] + dp[i+a[i]]
        // dp[i]: 表示从i开始能获得的分

        int max = -1;
        int[] dp = new int[a.length];
        for(int i=dp.length-1; i>=0; i--){
            if(i+a[i] > dp.length-1){
                dp[i] = a[i];
            } else {
                dp[i] = a[i] + dp[i+a[i]];
            }
            max = Math.max(max, dp[i]);
        }

        return max;

    }

//    private static int fun2(int[] a) {
//        // 模拟 举出 a[0] - a[0+a[0]]
//        int max = 0;
//        int end = a.length;
//        if(a[0] < a.length){
//            end = a[a[0]];
//        }
//
//        for (int i = 0; i < end; i++) {
//
//            int count = 0;
//            int index = i;
//            while (index < a.length) {
//                count += a[index];
//                index += a[index];
//            }
//            max = Math.max(max, count);
//
//        }
//
//
//        return max;
//    }


    private static void tencent1() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] res = new int[T];
        int index = 0;

        while (index < T) {
            int x = sc.nextInt();
            res[index] = fun1(x);
            index++;
        }
        for(int i: res){
            System.out.println(i);
        }
    }

    public static int fun1(int x){
        // 质数因子
        List<Integer> prim = getPrimes(100000);
        int first = 1;
        int second = 0;
        int third = 0;
        int index = 0;
        for (int i = 1; i < prim.size(); i++) {
            if(prim.get(i) - first >= x){
                second = prim.get(i);
                index = i;
                break;
            }
        }
        for (int i = index; i < prim.size(); i++) {
            if(prim.get(i) - second >= x){
                third = prim.get(i);
                break;
            }
        }


        return second * third;
    }

    public static List<Integer> getPrimes(int n){
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);

        for (int i = 2; i*i < n; i++) {
            if(isPrimes[i]){
                // 倍数全置为false
                for (int j = i*i; j < n; j+=i) {
                    isPrimes[j] = false;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if(isPrimes[i]){
                res.add(i);
            }
        }
        return res;
    }

}
