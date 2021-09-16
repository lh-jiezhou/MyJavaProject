package com.lh.test;

/**
 * 父子 构造方法关系
 */
class Fu {
    public Fu(){
        System.out.println("Fu, No par");
    }
//    private Fu(String str){
//        System.out.println("Fu, parameter");
//    }

    public Fu(String str){
        System.out.println("Fu, parameter");
    }
}
public class Test12 extends Fu{

    public Test12(String str){
//        super("test");
        System.out.println("Zi, parameter");
    }

    public static void main(String[] args) {
        Test12 t = new Test12("Test");
    }

}
