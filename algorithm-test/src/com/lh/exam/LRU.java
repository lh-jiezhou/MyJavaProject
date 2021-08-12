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

    /**
     * 容量
     */
    static private int capacity;
    static private Map<Integer, Integer> map;

    public static void main(String[] args) {
        LRU test = new LRU();
        Scanner sc = new Scanner(System.in);
        capacity = Integer.valueOf(sc.nextLine().trim());
        // 遍历LinkedHashMap会按照插入顺序访问元素
        map = new LinkedHashMap<>();
        while(sc.hasNextLine()){
            // 一行 去除两端空格
            String command = sc.nextLine().trim();
            if(capacity > 0 && command.charAt(0) == 'p'){
                int key = Integer.valueOf(command.substring(2, command.lastIndexOf(" ")));
                // 截取至末尾
                int value = Integer.valueOf(command.substring(command.lastIndexOf(" ")+1));
                test.put(key, value);
            } else if(capacity >= 0 && command.charAt(0) == 'g'){
                // 截取到末尾
                int key = Integer.valueOf(command.substring(2));
                System.out.println(test.get(key));
            }
        }
    }

    int get(int key){ // 取得值,更新优先级顺序
        if(map.containsKey(key)){
            int value = map.get(key);
            map.remove(key);
            // 重新插入以更新优先级
            map.put(key, value);
            return value;
        }
        // 不存在返回-1
        return -1;
    }

    // 插入值,旧key更新值,不更新优先级
    void put(int key, int value){
        // 存在key, 仅更新值,不更新优先级
        if(map.containsKey(key)){
            // ?? 此方法会不会更新优先级
            map.replace(key, value);
        } else { // 新值
            // 容量够
            if(map.size() < capacity){
                map.put(key, value);
            } else { // 容量不够,删除元素,更新优先级
                Iterator it = map.keySet().iterator();
                // 移出最先插入的
                map.remove(it.next());
                map.put(key, value);
            }
        }
    }


}
