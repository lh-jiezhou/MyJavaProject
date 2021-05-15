package com.lh;

public class TempAlgorithm {

    public static TempAlgorithm t1=new TempAlgorithm();


//    public static int i = 5;
    static {
        System.out.println("static ");
        System.out.println("blockB");
    }
    public static int i = 5;

    {
        System.out.println(11);
        System.out.println("blockA");
    }

    public TempAlgorithm() {
        System.out.println("construct");
    }

    public static void main(String[] args){
        System.out.println("main");
        System.out.println(i);
        TempAlgorithm t2=new TempAlgorithm();
    }
}