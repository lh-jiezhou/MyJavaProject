package com.lh.base;

/**
 * 数组实现一个队列 https://www.cnblogs.com/han200113/p/11526956.html
 *      1. 直接队列, 数组只能使用一次
 *          front = -1; 头; rear = -1; 尾
 *      2. 环形队列
 *          front = 0; rear = 0;
 *
 */
public class ArrayQueue {

    private int maxSize;
    private int[] arr;
    private int front;
    private int rear;

    // 初始化
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判空
    public boolean isEmpty(){
        return rear == front;
    }

    // 判满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    // 添加
    public void add(int num){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        arr[rear] = num;
        rear = (rear+1)%maxSize;
    }

    // 删除
    public int remove(){
        if(isEmpty()){
            new RuntimeException("队列为空");
        }
        int res = arr[front];
        front = (front+1)%maxSize;
        return res;
    }

    // 取队头
    public int peek(){
        if (isEmpty()){
            new RuntimeException("队列为空");
        }
        return arr[front];
    }

    // 显示所有数据
    public void allData(){
        if (isEmpty()){
            new RuntimeException("队列为空");
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d \n", i%maxSize, arr[i%maxSize]);
        }
    }

    // 取大小
    public int size(){
        if(isEmpty()){
            return 0;
        }
        return (maxSize-front+rear)%maxSize;
    }
}
