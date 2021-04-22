package com.lh.service.Impl;

import com.lh.dao.Impl.UserDaoImpl;
import com.lh.dao.Impl.UserDaoMysqlImpl;
import com.lh.dao.UserDao;
import com.lh.service.UserService;

public class UserServiceImpl implements UserService {

//    // 传统方式调用 dao
//    UserDao userDao = new UserDaoImpl();
//    // 更改需要修改原代码
//    UserDao userDao = new UserDaoMysqlImpl();

    private UserDao userDao;

    // 所以使用 set 用户注入;
    // 控制反转IOC 原型，把控制权给用户
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }

}
