package com.lh.exam;

import java.util.*;

/**
 * 实现LRU Cache的功能(Least Recently Used – 最近最少使用缓存)。
 *      它支持如下2个操作:
 *    1、int get(int key)
 *    2、void put(int key, int value)
 *      如果容量达到了限制，LRU Cache需要在插入新元素之前，将最近最少使用的元素删除
 *   ACM形式 手动输入输出
 *   样例：
 *   2        //Cache容量为2
 *  p 1 1    //put(1, 1)
 *  p 2 2    //put(2, 2)
 *  g 1      //get(1), 返回1
 *  p 2 102  //put(2, 102)，更新已存在的key，不算被使用
 *  p 3 3    //put(3, 3)，容量超过限制，将最近最少使用的key=2清除
 *  g 1      //get(1), 返回1
 *  g 2      //get(2), 返回-1
 *  g 3      //get(3), 返回3
 *
 *  使用LinkedHashMap数据结构 会按照插入顺序访问元素
 */
public class LRU {

    static private int capacity; // 容量
    static private Map<Integer, Integer> map;

    public static void main(String[] args) {
        LRU test = new LRU();
        Scanner sc = new Scanner(System.in);
        capacity = Integer.valueOf(sc.nextLine().trim());
        map = new LinkedHashMap<>(); // 遍历LinkedHashMap会按照插入顺序访问元素
        while(sc.hasNextLine()){
            String command = sc.nextLine().trim(); // 一行 去除两端空格
            if(capacity > 0 && command.charAt(0) == 'p'){
                // 输入精髓
                int key = Integer.valueOf(command.substring(2, command.lastIndexOf(" ")));
                int value = Integer.valueOf(command.substring(command.lastIndexOf(" ")+1)); // 截取至末尾
                test.put(key, value);
            } else if(capacity >= 0 && command.charAt(0) == 'g'){
                int key = Integer.valueOf(command.substring(2)); // 截取到末尾
                System.out.println(test.get(key));
            }
        }
    }

    int get(int key){ // 取得值,更新优先级顺序
        if(map.containsKey(key)){
            int value = map.get(key);
            map.remove(key);
            map.put(key, value); // 重新插入以更新优先级
            return value;
        }
        return -1; // 不存在返回-1
    }

    void put(int key, int value){ // 插入值,旧key更新值,不更新优先级
        if(map.containsKey(key)){ // 存在key, 仅更新值,不更新优先级
            map.replace(key, value); // ?? 此方法会不会更新优先级
        } else { // 新值
            if(map.size() < capacity){ // 容量够
                map.put(key, value);
            } else { // 容量不够,删除元素,更新优先级
                Iterator it = map.keySet().iterator();
                map.remove(it.next()); // 移出最先插入的
                map.put(key, value);
            }
        }
    }


}
