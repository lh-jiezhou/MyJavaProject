package com.lh;

public class Test {
    private String name = new String("abc");
//    private String name = "abc";
    public static void main(String[] args) {
        Test test = new Test();
        Test testB = new Test();
        String result = test.equals(testB) + ",";
        result += test.name.equals(testB.name) + ",";
        result += test.name == testB.name;
        System.out.println(result);
    }
}