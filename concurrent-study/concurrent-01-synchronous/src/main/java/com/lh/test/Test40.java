package com.lh.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 字段更新器
 *      用于保证多个线程访问对象里的成员变量(对象属性)时的线程安全性
 *      AtomicReferenceFieldUpdater
 *      AtomicIntegerFieldUpdater
 *      AtomicLongFieldUpdater
 */
@Slf4j(topic = "c.Test40")
public class Test40 {

    public static void main(String[] args) {
        Student stu = new Student();

        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");

        System.out.println(updater.compareAndSet(stu, null, "张三"));
        System.out.println(stu);
    }
}

class Student {
    volatile String name; // volatile

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}