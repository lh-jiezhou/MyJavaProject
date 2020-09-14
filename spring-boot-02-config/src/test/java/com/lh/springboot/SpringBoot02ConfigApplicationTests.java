package com.lh.springboot;

import com.lh.springboot.bean.Person;
import com.lh.springboot.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 *
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBoot02ConfigApplicationTests {

    // 自动注入
    @Autowired
    Person person;

    // 直接注入容器
    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean( "helloService");
        System.out.println(b);
    }

    @Test
    void contextLoads() {
        // 控制台输出
        System.out.println(person);
    }


}
