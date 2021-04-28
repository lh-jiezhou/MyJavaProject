package com.lh.pojo;

public class User {
    private String name;

    public User(){
        System.out.println("无参构造");
    }

    public User(String name){
        this.name = name;
        System.out.println("有参构造");
    }

    public void show(){
        System.out.println("name:"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
