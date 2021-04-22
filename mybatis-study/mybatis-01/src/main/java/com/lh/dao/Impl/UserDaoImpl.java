package com.lh.dao.Impl;

import com.lh.dao.UserDao;
import com.lh.pojo.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    // 传统JDBC 在此方法中写 操作数据库代码
    @Override
    public List<User> getUserList() {

        // 执行SQL
        // 得到结果集
        return null;
    }

    // Mybatis 通过 Mapper.xml 文件实现操作数据库

}
