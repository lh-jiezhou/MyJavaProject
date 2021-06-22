package com.lh.test;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 对象解析
 *      反射机制获取
 *      sun.misc.需要改成 jdk8 运行； file -> project structure
 */
public class TestUnsafe {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 反射机制获取
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        System.out.println(unsafe);

        // 1. 获取域的偏移地址
        long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        Teacher t = new Teacher();
        // 2. 执行 cas 操作
        unsafe.compareAndSwapInt(t, idOffset, 0, 1);
        unsafe.compareAndSwapObject(t, nameOffset, null, "张三");

        // 3. 验证
        System.out.println(t);
    }
}

@Data
class Teacher {
    volatile int id;
    volatile String name;
}


