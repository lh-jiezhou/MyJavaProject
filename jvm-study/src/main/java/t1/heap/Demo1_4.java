package t1.heap;

/**
 * 演示堆内存 （windows下）
 *  jps：查看java进程
 *  jmap：查看堆内存占有情况 jmap -heap 进程id
 *      注：jdk8之后 jhsdb jmap --heap --pid 16279
 *  jconsole：图形界面，可连续检测
 */
public class Demo1_4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000); // 30s
        byte[] array = new byte[1024 * 1024 * 10]; // 10 MB 堆内存
        System.out.println("2...");
        Thread.sleep(30000);
        array = null; // 引用置为空, 意味着byte对象可以被垃圾回收了
        System.gc(); // 显式的调用GC
        System.out.println("3...");
        Thread.sleep(1000000L);

    }
}
