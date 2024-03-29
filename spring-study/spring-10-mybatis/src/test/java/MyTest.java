import com.lh.mapper.UserMapper;
import com.lh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {


    @Test
    public void test() throws IOException {

        /**
         * 连接数据库
         */
        String resources = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resources);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sessionFactory.openSession(true);

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.selectUser();

        for (User user: userList){
            System.out.println(user);
        }

    }

    @Test
    public void test2() {
        /**
         * spring配置 连接数据库
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        UserMapper userMapper = context.getBean("userMapper", UserMapper.class); // 方式一
        UserMapper userMapper = context.getBean("userMapper2", UserMapper.class); // 方式二

        for (User user: userMapper.selectUser()){
            System.out.println(user);
        }
    }
}
