package com.lh.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据类型 value (或者 <key, value>)可否为 空
 *      ArrayList: value 可以为 null
 *      ConcurrentHashMap: key 和 value 都不能为 null
 *      HashMap: key 和 value 都能为 null (key 不重复)
 *      HashSet: value 可以为 null
 *
 */
public class Test11 {

    public static void main(String[] args) {
        Queue<Character> queue = new LinkedList<Character>();
        Stack<Character> stack = new Stack<>();

        // 1. ArrayList
        ArrayList arrayList = new ArrayList();
        arrayList.add(null);
        System.out.println("ArrayList: " + arrayList.size());

        // 2. ConcurrentHashMap
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "11");
//        concurrentHashMap.put(1, null);
//        concurrentHashMap.put(null, "11");
        System.out.println("ConcurrentHashMap: " + concurrentHashMap.size());

        // 3. HashMap
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, null);
        map.put(1, "33");
        map.put(null, null);
        System.out.println("HashMap: " + map.size());


        // 4. HashSet
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(null);
        set.add(null);
        System.out.println("HashSet: " + set.size());

        // 5.
        Map<String, Object> map11 = new ConcurrentHashMap<>();
        try {
            map11.put(null, new Object());
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            map11.put(" ", null);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(map11.size());


    }


}
