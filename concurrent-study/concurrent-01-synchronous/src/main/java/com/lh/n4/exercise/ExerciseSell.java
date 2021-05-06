package com.lh.n4.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static com.lh.n4.exercise.ExerciseTransfer.randomAmount;

@Slf4j(topic = "c.ExerciseSell")
public class ExerciseSell {

    public static void main(String[] args) throws InterruptedException {
        // 模拟多人买票
        TicketWindow window = new TicketWindow(1000);

        //  所有线程的集合
        List<Thread> threadList = new ArrayList<>();
        // 卖出的票数统计
        List<Integer> amountList = new Vector<>(); // 线程安全
        for(int i=0; i<4000; i++){ // 两千人来买票
            Thread thread = new Thread(() -> {
                // 买票操作
                int amount = window.sell(randomAmount());
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }

        // 等待所有线程执行完毕
        for(Thread thread: threadList){
            thread.join();
        }

        // 统计卖出的票数 和 剩余票数
        log.debug("余票：{}", window.getCount());
        // 使用流stream 做累加
        log.debug("卖出的票数：{}", amountList.stream().mapToInt(i -> i).sum());

    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~5
    public static int random(int amount) {
        return random.nextInt(amount) + 1;
    }
}

// 售票窗口
class TicketWindow {
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    // 获取余票数量
    public int getCount() {
        return count;
    }

    // 售票 (注意有无线程安全问题)
    public synchronized int sell(int amount) {
        // 属于临界区
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}