package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 *
 */
@Slf4j(topic = "c.Test35")
public class Test35 {

    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountCas(new BigDecimal("10000")));

    }

}

class DecimalAccountCas implements DecimalAccount{

//    private BigDecimal balance; // 不能保证线程安全
    private AtomicReference<BigDecimal> balance; // 改进


    public DecimalAccountCas(BigDecimal balance) {
//        this.balance = balance;
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {

        while (true) {
            BigDecimal prev = balance.get(); // 旧值
            BigDecimal next = prev.subtract(amount); // 减法
            if(balance.compareAndSet(prev, next)){
                break;
            }
        }
    }
}



interface DecimalAccount {
    // 获取金额
    BigDecimal getBalance();

    // 取款
    void withdraw(BigDecimal amount);

    /**
     * 方法内会启动 1000 个线程，每个线程做 -10 元 的操作
     * 如果初始余额为 10000 那么正确的结果应当是 0
     */
    static void demo(DecimalAccount account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
//                account.withdraw(new BigDecimal("10"));
                account.withdraw(BigDecimal.TEN);
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println(account.getBalance()
                + " cost: " + (end-start)/1000_000 + " ms");
    }
}
