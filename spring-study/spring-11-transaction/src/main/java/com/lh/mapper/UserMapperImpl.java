package com.lh.mapper;

import com.lh.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * 方式一：新建SqlSessionTemplate
 */
public class UserMapperImpl implements UserMapper {

    // 所有操作，原来都使用sqlSession来执行 ； 现在都使用SqlSessionTemplate

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {

        User user = new User(5,"小王","123456");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //
        mapper.addUser(user);
        mapper.deleteUser(5);

        return mapper.selectUser();
    }

    @Override
    public void addUser(User user) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(id);
    }
}
