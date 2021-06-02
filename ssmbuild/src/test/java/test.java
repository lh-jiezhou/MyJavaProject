import com.lh.pojo.Books;
import com.lh.service.BooksService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BooksService booksService = (BooksService) context.getBean("BooksServiceImpl");

        for(Books book: booksService.queryAllBook()){
            System.out.println(book);
        }

    }
}
