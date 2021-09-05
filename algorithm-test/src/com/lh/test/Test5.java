package com.lh.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 循环遍历删除 List 中的元素 https://blog.csdn.net/bbj12345678/article/details/84876662
 *      1. 使用普通for循环遍历
 *
 *      2. 使用增强型for循环遍历
 *          Exception in thread "main" java.util.ConcurrentModificationException
 *      3. 使用iterator遍历 (推荐)
 *
 *   new CopyOnWriteArrayList<>(); 可以直接使用增强 for 删除 (线程安全)
 *
 */
public class Test5 {

    @Test
    void test1(){
        List<String> aa = new ArrayList<String>();
        aa.add("F1");
        aa.add("F2");
        aa.add("F3");

        for (int i=0; i<aa.size(); i++) {
            if ("F3".equals(aa.get(i))) {
                aa.remove(aa.get(i));
                // 改变索引 !!
//                i--;
            }
        }
        for (String temp : aa){
            System.out.println(temp);
        }
    }

    /**
     * 2. 增强for
     */
    @Test
    void test2(){
        List<String> aa = new ArrayList<String>();
        aa.add("F1");
        aa.add("F2");
        aa.add("F3");
        // 删除的时候会改变list的index索引和size大小 , 异常
        for (String temp : aa) {
            if ("F3".equals(temp)) {
                aa.remove(temp);
            }
        }
        for (String temp : aa){
            System.out.println(temp);
        }

    }

    /**
     * 3. 迭代器 iterator
     */
    @Test
    void test3(){
        List<String> aa = new ArrayList<String>();
        aa.add("F1");
        aa.add("F2");
        aa.add("F3");

        Iterator<String> it = aa.iterator();
        while(it.hasNext()){
            String temp = it.next();
            if ("F3".equals(temp)) {
                it.remove();
            }
        }
        for (String temp : aa){
            System.out.println(temp);
        }
    }
}
