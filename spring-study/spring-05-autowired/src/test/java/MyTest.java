import com.lh.pojo.People;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    void test1(){
        /**
         * 自动注入
         *  byName
         *  byType
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people", People.class);
        people.getCat().shout();
        people.getDog().shout();

    }
}
