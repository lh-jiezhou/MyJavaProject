import com.lh.dao.UserMapper;
import com.lh.pojo.User;
import com.lh.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class MyTest {


    /**
     * 一级缓存；在一次 SqlSession 中有效, （也就是拿到连接到关闭连接这个区间段）
     */
    @Test
    public void test1() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);

//        sqlSession.clearCache(); // 清理缓存
        mapper.updateUser(new User(1, "节宙", "789"));

        System.out.println("==============");
        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println(user == user1);

        sqlSession.close();

    }


    /**
     * 二级缓存；在一次 mapper.xml 中(namespace)有效,
     */
    @Test
    public void test2(){
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.queryUserById(1);
        System.out.println(user1);
        sqlSession1.close();

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);

        System.out.println(user1 == user2);


//        sqlSession1.close();
        sqlSession2.close();

    }
}
