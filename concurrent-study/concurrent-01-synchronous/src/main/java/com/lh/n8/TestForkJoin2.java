package com.lh.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin
 *      分治思想
 *      线程拆分
 *      适用于密集型CPU
 *
 * jdk8 之后简化了任务拆分
 */

public class TestForkJoin2 {

    public static void main(String[] args) {
        // 1. 线程池对象 默认是与cpu核心数相等
        ForkJoinPool pool = new ForkJoinPool(4);
        // System.out.println(pool.invoke(new MyTask(5)));
        System.out.println(pool.invoke(new AddTask3(1, 5)));

        // 拆分 new MyTask(5) = 5 + new MyTask(4) ...

    }
}

/**
 * 求 1~n 之间整数和
 */
@Slf4j(topic = "c.MyTask")
class MyTask extends RecursiveTask<Integer>{

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + "}";
    }

    @Override
    protected Integer compute() {
        // 1. 终止条件
        if(n == 1){
            log.debug("join() {}", n);
            return 1;
        }

        // 2. 将任务进行拆分 fork
        MyTask t1 = new MyTask(n - 1);
        // 让一个线程去执行此任务
        t1.fork();
        log.debug("fork() {} + {}", n, t1);

        // 3. 获取任务结果 并合并结果 join
        int result = n + t1.join();
        log.debug("join() {} + {} = {}", n, t1, result);

        return result;
    }
}

@Slf4j(topic = "c.AddTask3")
class AddTask3 extends RecursiveTask<Integer> {

    int begin;
    int end;
    public AddTask3(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }
    @Override
    protected Integer compute() {
        // 5, 5
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }
        // 4, 5 相邻时
        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, end + begin);
            return end + begin;
        }

        // 1 5
        int mid = (end + begin) / 2;
        // 1,3
        AddTask3 t1 = new AddTask3(begin, mid);
        t1.fork();
        // 4,5
        AddTask3 t2 = new AddTask3(mid + 1, end);
        t2.fork();
        log.debug("fork() {} + {} = ?", t1, t2);
        int result = t1.join() + t2.join();
        log.debug("join() {} + {} = {}", t1, t2, result);
        return result;
    }
}
