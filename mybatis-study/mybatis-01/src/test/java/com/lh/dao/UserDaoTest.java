package com.lh.dao;

import com.lh.pojo.User;
import com.lh.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void test1(){
        // 获取sqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try{
            // 执行SQL 方式一：getMapper
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> userList = userDao.getUserList();
            for(User user: userList){
                System.out.println(user.toString());
            }
            // 方式二: 以前的方法(不推荐)
            List<User> userList1 = sqlSession.selectList("com.lh.dao.UserDao.getUserList");
            for(User user: userList1){
                System.out.println(user.toString());
            }
        }
        finally {
            // 记得 关闭SqlSession
            sqlSession.close();
        }
    }

//    public static void main(String[] args) {
//
//    }

}
