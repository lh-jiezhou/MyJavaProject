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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
