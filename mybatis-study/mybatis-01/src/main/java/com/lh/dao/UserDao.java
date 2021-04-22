package com.lh.dao;

import com.lh.pojo.User;

import java.util.List;

// 规范命名 UserMapper
public interface UserDao {

    List<User> getUserList();

}
