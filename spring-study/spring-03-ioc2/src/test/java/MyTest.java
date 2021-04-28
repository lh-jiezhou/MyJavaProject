import com.lh.pojo.User;
import com.lh.pojo.UserT;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    void test1(){
        User user = new User();
    }

    @Test
    void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = (User) applicationContext.getBean("user");
        // UserT同时也被实例化了
        User user2 = (User) applicationContext.getBean("user");
        user.show();
        System.out.println(user == user2);

        User userNew = (User)applicationContext.getBean("userNew");
        user.show();
    }
}
