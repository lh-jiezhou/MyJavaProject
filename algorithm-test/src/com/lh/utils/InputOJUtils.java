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
 *
 * hasNext（） 是检测 还有没有下一个输入
 * hasNextLine（） 是检测下一行有没有输入
 *
 * next（）是指针移动到当前下标，并取出下一个输入
 * nextLine（） 把指针移动到下一行 让然后取出当前这一行的输入

 */
package com.lh.utils;

import java.util.Scanner;

public class InputOJUtils {

    // 测试反射 Test3.java
    private int temp;
    public InputOJUtils(){}
    public InputOJUtils(int temp) { this.temp = temp; }
    public void setTemp(int temp) { this.temp = temp; }
    @Override
    public String toString() {
        return "InputOJUtils{" +
                "temp=" + temp +
                '}';
    }

    public static void main(String[] args){
        InputOJUtils test = new InputOJUtils();
        test.inputIntAndStr();
//        test.inputNum();  // 数字
//        test.inputNumEOF(); // 以EOF结束 或 Ctrl+Z
//        test.inputStr(); // 输入字符串
//        test.inputSingle(); //单行输入多个参数
//        test.inputArray(); // 输入数组
//        test.inputDoubleDimensionArray(); // int 二维数组
//        test.inputDoubleDimensionChar(); // char 二维数组
    }

    // int与String交叉输入 方式
    void inputIntAndStr(){
        Scanner sc = new Scanner(System.in);
        // 先输入数字 再输入字符串，不正确；
        // 由于java虚拟式识别的问题
//        int a = sc.nextInt();
//        String s1 = sc.nextLine();

        // 处理方式1: 类型转型
//        String tmp = sc.nextLine();
//        int a = Integer.parseInt(tmp);
//        String s1 = sc.nextLine();

        // 处理方式2: 多出一行
        int a = sc.nextInt();
        sc.nextLine(); //
        String s1 = sc.nextLine();

        // 处理方式3: 再新建一个Scanner对象

        System.out.println(a);
        System.out.println(s1);
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
//        String s1 = sc.next(); // 同行输入(空格间隔) 与 换行输入
//        String s2 = sc.next();

        // 怎样完成 int 与 nextLine() 交叉输入？？？
//        int a = sc.nextInt();
        String s1 = sc.nextLine(); // 同行 - 输入有空格也会包含进去
        String s2 = sc.nextLine();
        int b = sc.nextInt();
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
