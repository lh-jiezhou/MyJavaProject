package com.lh;

import java.util.*;

public class TempAlgorithm {

    public static void main(String[] args) {
        System.out.println(true || true && false );
    }

    public int countPalindromicSubsequence1(String s) {
        int len = s.length();
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> seen = new HashSet<>();



        for (int i = 0; i < len; i++) {
            // 添加 遇到相同 则更新
            map.put(s.charAt(i), i);
        }
        for (int i = 0; i < len - 2; i++) {
            // 以该字母 为首尾的已经 计算
            if (seen.contains(s.charAt(i))) {
                continue;
            }
            // 记录 首尾相同的 字母范围内 不同字母个数
            Set<Character> set = new HashSet<>();
            // 取到与当前字母相同的最后一个位置
            int end = map.get(s.charAt(i));
            // 计数
            for (int j = i + 1; j < end; j++) {
                set.add(s.charAt(j));
            }
            // 相加
            count += set.size();
            // 标记当前字母 为首尾 的 已经计算
            seen.add(s.charAt(i));
        }
        return count;
    }


    /**
     * 长度为 3 的不同回文子序列
     * @param s
     * @return
     */
    public static int countPalindromicSubsequence(String s) {
        // set 去重
        Set<String> set = new HashSet<String>();
        StringBuilder path = new StringBuilder();
        // !! 记录该数字是否被使用过 初始false
        boolean[] used = new boolean[s.length()];
        char[] cs = s.toCharArray();
        int len = s.length();

        dfs(cs, path, 0, len, set, used);

        return set.size();
    }

    public static void dfs(char[] charArray, StringBuilder path, int index, int len, Set<String> set, boolean[] used){

        if(path.length() == 3 || index == len){
            if(isPalindrome(path.toString())){
                set.add(path.toString());
            }
            return;
        }

        for(int i=0; i<len-2; i++){

            if(path.length() ==  0 && i>=len-3){
                continue;
            }

            // 未使用
            if(!used[i]) {
                path.append(charArray[i]);
                used[i] = true;
                dfs(charArray, path, index + 1, len, set, used);

                used[i] = false;
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    /**
     *
     * @param s 长度为3
     * @return
     */
    public static boolean isPalindrome(String s){
        return s.charAt(0) == s.charAt(2);
    }



    public int[] getConcatenation(int[] nums) {

        int[] ans = new int[2 * nums.length];
        for(int i=0; i<nums.length; i++){
            ans[i] = nums[i];
        }
        for(int i=0; i<nums.length; i++){
            ans[i+nums.length] = nums[i];
        }
        return ans;


    }




    /**
     * 统计消灭怪物个数
     * @param dist 距离为0时失败
     * @param speed
     * @return
     */
    static public int eliminateMaximum(int[] dist, int[] speed) {

        List<Integer> time = new ArrayList();

        int count = 0;
        boolean isFail = false;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while(!isFail && count<dist.length){
            count++;
            // 更新距离距离；并消灭一个, 下次可能失败的点
            for (int i = 0; i < dist.length; i++) {
                int d = dist[i] - speed[i];
                if(d <= min) {
                    min = d;
                    index = i;
                }
                // 更新距离
                dist[i] = d;
            }
            // 消灭
            dist[index] = Integer.MAX_VALUE;
            // 判断是否失败
            for (int i = 0; i < dist.length; i++) {
                if(dist[i] <= 0){
                    isFail = true;
                    break;
                }
            }
        }
        return count;
    }



    /**
     * 计算器
     * @param s
     * @return
     */
    static public int calculate(String s) {
        // 遇到数字 入栈; 遇到 */ 直接运算; 遇到 +- 入栈
        Stack<Integer> stackNum = new Stack<>();
        // 临时数字
        int num = 0;
        char preSign = '+';

        for (int i = 0; i < s.length(); i++) {
            // 是数字
            if(Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }
            // 是运算符 或者 是最后一个
            boolean isSign = (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == s.length()-1;
            if(isSign){
                switch (preSign) {
                    case '+': stackNum.push(num); break;
                    case '-': stackNum.push(-num); break;
                    case '*': stackNum.push(stackNum.pop() * num); break;
                    case '/': stackNum.push(stackNum.pop() / num); break;
                    default: break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }

        int ans = 0;
        while (!stackNum.isEmpty()){
            ans += stackNum.pop();
        }
        return ans;
    }


    /**
     * 最大滑动窗口
     * @param nums
     * @param k
     * @return
     */
    static public int[] maxSlidingWindow(int[] nums, int k) {

        int count = nums.length - k + 1;
        int[] res = new int[count];
        // 默认升序; 重写排序 为降序
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((Integer a, Integer b) -> {
            return b - a;
        });
        for(int i = 0; i < k; i++){
            queue.offer(nums[i]);
        }

        for (int i = 0; i < count - 1; i++) {
            res[i] = queue.peek();
            queue.remove(nums[i]);
            queue.offer(nums[i + k]);
        }
        res[count-1] = queue.peek();
        return res;

    }


    /**
     * 前k高频元素
     * @param nums
     * @param k
     * @return
     */
    static public int[] topKFrequent(int[] nums, int k) {

        // hash
        int[] res = new int[k];
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        // 优先队列 小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((int[] o1, int[] o2) -> {
            return o1[1] - o2[1];
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            // 保持 数量最大的 k 个元素
            if (queue.size() == k) {
                if (count > queue.peek()[1]) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }

        return res;
    }
}