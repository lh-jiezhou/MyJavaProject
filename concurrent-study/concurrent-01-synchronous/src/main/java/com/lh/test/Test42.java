package com.lh.test;


import com.lh.n4.UnsafeAccessor;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

/**
 * 模拟实现原子整数类
 *      （写源码）
 */
@Slf4j(topic = "c.Test42")
public class Test42 {
    public static void main(String[] args) {
        Account.demo(new MyAtomicInteger(10000));
    }
}

class MyAtomicInteger implements Account {
    private volatile int value;
    private static final long valueOffset; // 要保护域的 偏移量
    private static final Unsafe UNSAFE; // Unsafe 对象
    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e); // 转换成运行时异常（非检查）
        }
    }

    public int getValue() {
        return value;
    }

    public void decrement(int amount) {
        while(true) {
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffset, prev, next)) {
                break;
            }
        }
    }



    public MyAtomicInteger(int value) {
        this.value = value;
    }

    @Override
    public Integer getBalance() {
        return getValue();
    }

    @Override
    public void withdraw(Integer amount) {
        decrement(amount);
    }
}
