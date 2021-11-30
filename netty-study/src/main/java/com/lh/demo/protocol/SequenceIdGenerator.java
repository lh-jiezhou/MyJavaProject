package com.lh.demo.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * rpc 实验中 生成 中对象id
 */
public class SequenceIdGenerator {

    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }
}
