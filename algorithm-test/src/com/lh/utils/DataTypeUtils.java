package com.lh.utils;

import java.util.*;

public class DataTypeUtils {

    public static void main(String[] args) {

        // 1、数组转List
        String[] str1 = new String[]{"Tom", "Bob", "Jane"};
        List<String> strList1 = Arrays.asList(str1);
//        System.out.println(strList1.get(1));


        // 1、List转数组
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1); listInt.add(2); listInt.add(3);
        Integer[] arr1 = listInt.toArray(new Integer[listInt.size()]); // 方法一
        for(int i: arr1) System.out.println(i);

        int[] arr2 = listInt.stream().mapToInt(Integer::intValue).toArray();
        for(int i: arr2) System.out.println(i);

        // 2、数组转Set
        String[] str2 = new String[]{"Tom", "Bob", "Jane"};
        Set<String> strSet1 = new HashSet<>(Arrays.asList(str2));
        strSet1.add("Mary");
        strSet1.add("Bob");
//        for(String s: strSet1) System.out.println(s);

        // 3、List转Set
        String[] str3 = new String[]{"Tom", "Bob", "Jane"};
        List<String> strList2 = Arrays.asList(str3);
        Set<String> strSet2 = new HashSet<>(strList2);
//        for(String s: strSet2) System.out.println(s);

        // 4、Set转List
        String[] str4 = new String[]{"Tom", "Bob", "Jane"};
        Set<String> strSet3 = new HashSet<>(Arrays.asList(str4));
        strSet3.add("Mary");
        List<String> strList3 = new ArrayList<>(strSet3);
//        System.out.println(strList3.get(3));


         // char 数组 转 String
         char[][] board = new char[2][2];
         List<String> list = new LinkedList<>();
         for(char[] chars: board){
            // 用法
            list.add(new String(chars));
         }


    }


}



