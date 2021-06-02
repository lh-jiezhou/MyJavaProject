import com.lh.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test1() {
        /**
         * 注解的方式 注册到Spring中
         *  @Component: 声明组件,说明这个类被Spring管理了，就是bean
         *      @Controller
         *      @Service
         *      @Repository
         *  @Scope("prototype")  // 配置作用域
         *  @Value("jiezhou") // 注入值
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);

        System.out.println(user.getName());
    }
}
