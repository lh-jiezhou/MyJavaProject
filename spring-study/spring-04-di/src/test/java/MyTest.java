import com.lh.pojo.Student;
import com.lh.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    void test1(){
        /**
         * 配置文件注入 复杂数据类型
         */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getName());
        System.out.println(student.toString());
    }

    @Test
    void test2(){
        /**
         * 配置文件中 使用p命名空间 (c命名空间注入)
         */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user.toString());
    }
}
