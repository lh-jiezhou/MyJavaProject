package com.lh.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 通过反射获取 HashMap 容量 capacity
 *      默认容量 16
 *      自定义容量 会向上逼近第一个 2^n
 *          例如: 定义 new HashMap(5); 实际大小 8
 *                定义 new HashMap(20); 实际大小 32
 *
 *      阈值：在初始化的时候，阈值是等于容量的；当放入第一个元素后，重新计算阈值，新的阈值=容量X负载因子。
 *
 *      size() 函数获取的仅仅是 HashMap 的元素个数
 */
public class Test7 {

    public static void main(String[] args) throws Exception {

        // 自定义初始容量 20
        HashMap m = new HashMap(8);

        // 获取类
        Class mapType = m.getClass();

        // 获取属性 threshold: 阈值
        Field threshold = mapType.getDeclaredField("threshold");
        // 目标属性设置可以访问
        threshold.setAccessible(true);

        // 获取方法 capacity(); 因为HashMap没有容量这个属性，但是capacity方法会返回容量值
        Method capacity = mapType.getDeclaredMethod("capacity");
        // 设置目标方法为可以访问
        capacity.setAccessible(true);

        System.out.println("容量: " + capacity.invoke(m) + " 阈值: " + threshold.get(m) + " 元素数量: " + m.size());
        System.out.println("==========初始==========");
        for (int i = 0; i < 25; i++) {
            m.put(i, i);
            System.out.println("容量: " + capacity.invoke(m) + " 阈值: " + threshold.get(m) + " 元素数量: " + m.size());
        }

    }
}
