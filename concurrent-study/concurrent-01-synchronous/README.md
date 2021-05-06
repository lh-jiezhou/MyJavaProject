## 文件目录结构


com.lh.test:
```java
/**
 *  Test1
 * 创建线程 Thread
 * 
 *  Test2
 * 创建线程 Runnable
 * 任务对象 线程对象 分离
 * 
 *  Test3
 * 多线程
 * 执行后 t1、t2 交替运行
 * 单核测试 会导致其他程序运行不了
 * 
 * Test4
 * run() 方法与 start()方法
 * run()在原来main线程上
 * 
 * Test5
 * 线程六种状态
 * NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED
 *(附: start() 只能调用一次 (RUNNABLE状态后不能再调用start ))
 * 
 * Test6
 * sleep()方法
 * RUNNABLE 变为 TIMED_WAITING 状态
 * 
 * Test7
 * interrupt()方法
 * 打断sleep()线程 （唤醒） 并提示异常
 * 
 * Test8
 * TimeUnit 设置sleep时间
 *  可读性更好
 *  
 * Test9
 * yield() 方法
 *  让出CPU 进入 Runnable态
 * setPriority() 方法
 *  社会线程优先级
 *  
 *  Test10
 * join() 方法
 *  等待线程运行结束
 *  (线程同步典型应用)
 *  com.lh.n3.TestJoin.java
 *  
 *  Test11
 * interrupt() 方法
 *     打断阻塞状态下的线程
 *          阻塞 sleep()、wait()、join()
 *
 *     isInterrupted()线程打断标记
 *          如果被打断过 就为 true
 *           但是 sleep,wait,join会重置打断标记为 false
 *  
 *  Test12
 * interrupt()方法
 *     打断正在运行的线程
 *      只是知道有线程在干扰我，并不会停止运行
 *     要停止运行，要靠打断标记
 *     即被打断(interrupt)线程，由自己决定要不要继续运行
 *  
 *  Test13
 * 两阶段终止模式 Two Phase Termination
 *    在一个线程 t1 中如何“优雅”终止线程 t2
 *    “优雅”指的是给 t2 一个料理后事的机会
 *  
 *  Test14
 * interrupt() 打断park() 让他继续运行
 *      打断后,打断标记为true， 此后再调用 park() 会失效
 *      可采用 Thread.interrupted() 返回标记并重置为 false
 *  
 *  Test15
 * Java进程需要等待所有线程运行结束，才会结束
 *      在log处设断点实验
 *      在if处添加断点
 *
 *  但只要其它非守护线程运行结束了
 *      只剩下守护线程Daemon  会强制结束
 *  
 *  Test16
 * 应用实例：烧水泡茶
 *      统筹
 *  
 *  
 *  
 *  
 */



```