package com.lh.mapper;

import com.lh.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectUser();

    // 增加
    void addUser(User user);

    // 删除
    void deleteUser(int id);

}
