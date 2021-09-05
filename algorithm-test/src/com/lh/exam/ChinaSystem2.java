package com.lh.exam;

import java.util.Scanner;

/**
 * 中国系统笔试
 *      第二题：链表实现有序序列
 *
 *      push (插入); 返回插入位置
 */
public class ChinaSystem2 {

    public static void main (String[] args) {
        // 完成主程序
        LinkQueue linkQueue = new LinkQueue();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] sArr = str.split(" ");
        long[] nums = new long[sArr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Long.parseLong(sArr[i]);
        }
        long[] res = new long[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = linkQueue.push(nums[i]);
        }
        for (long l: res){
            System.out.print((int)l + " ");
        }
    }
}

class LinkQueue {
    private QNode front; //队头指针

    //进队
    public Long push(Long num) {
        // 实现push方法
        if(front == null){
            front = new QNode(num);
            front.next = null;
            return 0L;
        }
        QNode head = front;
        QNode cur = new QNode(num);
        long index = 0;
        while(head != null){
            long temp = head.getData();
            if(num > temp){
                if(head.next == null){
                    head.next = cur;
                    index++;
                    break;
                }
                head = head.next;
                index++;
            } else {
                cur.next = head.next;
                head.next = cur;
                return index;
            }
        }

        head.next = cur;
        return index;

    }
}

//链队结点
class QNode {
    private Long data;//数据域
    public QNode next;//指针域

    //初始化1
    public QNode() {
        this.data = null;
        this.next = null;
    }

    //初始化2
    public QNode(Long data) {
        this.data = data;
        this.next = null;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }
}
