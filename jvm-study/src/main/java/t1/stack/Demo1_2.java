package t1.stack;

/**
 * 演示栈内存溢出 StackOverflowError
 * -Xss256k (-Xss设置栈的大小)
 */
public class Demo1_2 {

    private static int count;

    public static void main(String[] args) {
        try {
            method1();
        } catch (Throwable e){
            e.printStackTrace();
            System.out.println(count); // 输出查看
        }
    }

    private static void method1(){
        count++;
        method1();
    }
}
