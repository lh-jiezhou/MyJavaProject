package com.lh.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * 原子整数 AtomicInteger
 *      相关方法 API
 */
public class Test34 {

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(6);

        System.out.println(i.incrementAndGet()); // ++i (保证原子性)
        System.out.println(i.getAndIncrement()); // i++
        System.out.println(i.get());

        System.out.println(i.addAndGet(5)); // 先add
        System.out.println(i.getAndAdd(5)); // 先get
        System.out.println(i.get());


        System.out.println(i.updateAndGet(new IntUnaryOperator(){
            @Override
            public int applyAsInt(int operand) {
                return operand * 2;
            }
        }));
        //                                  读取值    设置值
        System.out.println(i.updateAndGet(value -> value / 2 ));

        // 方式一：updateAndGet 实现原理
        while(true) {
            int prev = i.get();
            int next = prev * 10;
            if(i.compareAndSet(prev, next)){
                break;
            }
        }
        // 方式二：updateAndGet 实现原理 抽象成方法
        updateAndGet(i, operand -> operand/2);

        // i.getAndUpdate()

        System.out.println(i.get());
    }

    public static int updateAndGet(AtomicInteger i, IntUnaryOperator operator){ // IntUnaryOperator运算
        while (true) {
            int prev = i.get();
//            int next = prev * 10; // 具体运算抽离出来
            int next = operator.applyAsInt(prev);

            if(i.compareAndSet(prev, next)){
                return next;
            }
        }
    }

}
