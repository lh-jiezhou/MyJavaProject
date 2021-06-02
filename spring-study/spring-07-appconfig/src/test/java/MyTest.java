import com.lh.config.MyConfig;
import com.lh.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        /**
         *  如果完全使用了配置类方法去做
         *        就只能通过 AnnotationConfig上下文来获取容器
         *        通过配置类的class对象加载
         */
        ApplicationContext context =  new AnnotationConfigApplicationContext(MyConfig.class);

        User user = context.getBean("getUser", User.class); // getUser 即为配置类中的方法名字
        System.out.println(user.getName());
    }
}
