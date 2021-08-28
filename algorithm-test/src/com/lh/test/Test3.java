package com.lh.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射实验: 动态获取类和对象的信息,以及动态调用对象的方法的功能
 *  对InputOJUtils
 *  API:
 *      Class类: 类的属性和方法
 *      Field类: 类的成员变量
 *      Method类: 类的方法
 *      Constructor类: 类的构造方法
 */
public class Test3 {

    public Test3() {
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 1、获取类的class对象
        Class clazz = Class.forName("com.lh.utils.InputOJUtils");
        // 2、获取类的方法
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m: methods){
            System.out.println(m.toString());
        }
        // 3、获取类的所有成员的属性信息
        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields){
            System.out.println(f.toString());
        }
        // 4、获取类的所有构造方法的信息
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for(Constructor c: constructors){
            System.out.println(c.toString());
        }

        // 动态调用方法
        Method method = clazz.getMethod("setTemp", int.class); // 获取setTemp方法; getMethod(name, parameterTypes)
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance(); // 新建对象(采用无参构造函数)
        method.invoke(object, 10); // 调用object的setTemp方法
        System.out.println(object.toString());


    }
}
