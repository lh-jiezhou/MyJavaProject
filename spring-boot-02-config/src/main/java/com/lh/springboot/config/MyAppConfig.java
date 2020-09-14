package com.lh.springboot.config;

import com.lh.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration: 指明当前类是一个配置类，代替之前的配置文件
 * 在配置文件中用 <bean></bean> 标签添加组件
 *
 */
@Configuration
public class MyAppConfig {

    // 将方法的返回值添加到容器中，容器中这个组件的默认id 就是方法名
    @Bean
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加了组件");
        return new HelloService();
    }
}
