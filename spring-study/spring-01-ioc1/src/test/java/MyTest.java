import com.lh.dao.Impl.UserDaoImpl;
import com.lh.dao.Impl.UserDaoMysqlImpl;
import com.lh.service.Impl.UserServiceImpl;
import com.lh.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {


    // 模拟用户, 用户实际调用的是业务层
//    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
//        userService.getUser();
//    }

    UserService userService = new UserServiceImpl();
    @Test
    public void testGet(){
        userService.getUser();
    }

    @Test
    public void test02(){
//        ((UserServiceImpl) userService).setUserDao(new UserDaoMysqlImpl());
        ((UserServiceImpl) userService).setUserDao(new UserDaoImpl());
        userService.getUser();
    }

    @Test
    public void test03(){
        // 获取 ApplicationContext, 拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("userServiceImpl");
        userServiceImpl.getUser();
    }
}
