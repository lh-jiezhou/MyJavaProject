package com.lh.dao.Impl;

import com.lh.dao.UserDao;

public class UserDaoMysqlImpl implements UserDao {

    @Override
    public void getUser() {
        System.out.println("获取用户信息Mysql实现");
    }
}
