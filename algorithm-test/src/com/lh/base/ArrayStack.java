package com.lh.base;

/**
 * 数组实现栈 参考：https://www.cnblogs.com/shadowdoor/p/9234242.html
 *      int top 指针定位
 */
public class ArrayStack {
    private int[] arr;
    private int maxSize;
    private int top;

    // 初始化
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 初始
        top = -1;
    }

    // 添加
    public void add(int value){
        if(isFull()){
            throw new RuntimeException("栈为满");
        }
        top += 1;
        arr[top] = value;
    }

    // 删除
    public int remove(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int res = arr[top];
        top -= 1;
        return res;
    }

    // 判满
    public boolean isFull(){
        return top == maxSize;
    }

    // 判空
    public boolean isEmpty(){
        return top == -1;
    }

    // 显示栈顶
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return arr[top];
    }

    // 输出所有元素



}
