/**
 * 输入
 * 格式1：Scanner sc = new Scanner (new BufferedInputStream(System.in));
 * 格式2：Scanner sc = new Scanner (System.in);
 * 在读入数据量大的情况下，格式1的速度会快些。
 * 读一个整数： int n = sc.nextInt(); 相当于 scanf("%d", &n); 或 cin >> n; 
 * 读一个字符串：String s = sc.next(); 相当于 scanf("%s", s); 或 cin >> s; 
 * 读一个浮点数：double t = sc.nextDouble(); 相当于 scanf("%lf", &t); 或 cin >> t; 
 * 读一整行： String s = sc.nextLine(); 相当于 gets(s); 或 cin.getline(...); 
 * 输出
 * System.out.print();
 * System.out.println();
 * System.out.format();
 * System.out.printf();
 * @param args
 */
package com.lh.utils;

import java.util.Scanner;

public class InputOJUtils {

    public static void main(String[] args){
        InputOJUtils test = new InputOJUtils();
//        test.inputNum();  // 数字
//        test.inputNumEOF(); // 以EOF结束 或 Ctrl+Z
        test.inputStr(); // 输入字符串
//        test.inputSingle(); //单行输入多个参数
//        test.inputArray(); // 输入数组
//        test.inputDoubleDimensionArray(); // int 二维数组
//        test.inputDoubleDimensionChar(); // char 二维数组
    }

    // 数字
    void inputNum(){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        System.out.println("m: " + m);
        System.out.println("n: " + n);
        System.out.format("%.2f\n", (m / (1.0 * n)));
        System.out.printf("%.2f", (m / (1.0 * n))).println();
        System.out.println("n: "+ n);
    }

    // 以EOF结束 或 Ctrl+Z
    void inputNumEOF(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(m + " " + n);
        }
    }

    // 输入字符串
    void inputStr(){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(); // nextLine(); 同行输入(空格间隔) 与 换行输入
        String s2 = sc.next();
        System.out.println("s1 length:"+s1.length());
        System.out.println(s1);
        System.out.println("s2 length:"+s2.length());
        System.out.println(s2);
    }

    //单行输入多个参数
    void inputSingle() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.trim().split(" ");
        for (String tmp : s) {
            System.out.println(tmp);
        }
    }

    // 输入数组
    void inputArray(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 数组长度
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        for(int x: arr){
            System.out.println(x);
        }
    }

    // int 二维数组
    void inputDoubleDimensionArray(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        //必须读取回车符 【很久之前的 吃掉回车符号】
        //sc.nextLine();
        int[][] map = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j] = sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    // char 二维数组
    private void inputDoubleDimensionChar(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 行
        int m = sc.nextInt();
        String[] temp = new String[n]; // //字符串数组做中间变量
        for(int i=0; i<n; i++){
            temp[i] = sc.next(); // 字符
        }
        char[][] p = new char[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                p[i][j] = temp[i].charAt(j); // 字符串转字符
                System.out.println(p[i][j]);
            }
        }
    }


}
