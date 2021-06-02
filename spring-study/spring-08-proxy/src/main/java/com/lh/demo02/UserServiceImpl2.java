package com.lh.demo02;

// 真实角色2
public class UserServiceImpl2 implements  UserService {
    @Override
    public void add() {
        System.out.println("增加了多个AA用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了多个AA用户");
    }

    @Override
    public void update() {
        System.out.println("更新了多个AA用户");
    }

    @Override
    public void query() {
        System.out.println("查询了多个AA用户");
    }
}
