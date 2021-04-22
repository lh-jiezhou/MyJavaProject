import com.lh.pojo.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test1(){
        // 获取Spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 对象现在都在Spring中管理
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
