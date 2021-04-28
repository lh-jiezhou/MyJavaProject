package com.lh.pojo;

public class UserT {
    private String name;

    //

    public UserT(){
        System.out.println("UserT无参构造");
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
