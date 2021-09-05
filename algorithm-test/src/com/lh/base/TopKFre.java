package com.lh.base;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前 k 高频 元素
 *      使用 优先队列 (大顶堆)
 *      维护 堆中 k 个元素
 */
public class TopKFre {

    public static void main(String[] args) {
        int[] nums = new int[]{1,22,3,3,3,1,44};
        int k = 2;
        int[] fres = TopKFre.topKFrequent(nums, k);

        for(int x: fres){
            System.out.print(" "+x);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {

        // hash
        int[] res = new int[k];
        HashMap<Integer, Integer> map = new HashMap();
        for(int num: nums){
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        // 优先队列 小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((int[] a, int[] b) -> {
            return a[1] - b[1];
        });

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            // 调整，保持前k高频
            if(queue.size() == k){ 
                if(count > queue.peek()[1]){
                    queue.poll();
                    queue.offer(new int[] {num, count});
                }
            } else {
                queue.offer(new int[] {num, count});
            }
        }
        for(int i = 0; i < k; i++){
            res[i] = queue.poll()[0];
        }
        return res;
    }
}
