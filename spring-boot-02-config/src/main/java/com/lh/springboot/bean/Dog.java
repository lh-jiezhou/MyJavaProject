package com.lh.springboot.bean;

import org.springframework.stereotype.Component;

@Component
public class Dog {

    private String name;
    private Integer age;

    // 快捷键 Alt+Insert
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
