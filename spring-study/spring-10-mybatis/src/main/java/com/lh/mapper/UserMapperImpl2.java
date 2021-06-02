package com.lh.mapper;

import com.lh.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 方式2: 继承SqlSessionDaoSupport
 *     不需要 SqlSessionTemplate
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper =  sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUser();

        // 简化
//        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
