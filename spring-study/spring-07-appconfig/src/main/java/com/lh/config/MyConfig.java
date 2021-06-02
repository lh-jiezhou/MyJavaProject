package com.lh.config;

import com.lh.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Configuration 代表这个是配置类，和之前的beans.xml类似
 * 这个类也会Spring容器托管，注册到容器中，@Configuration本来就是一个@Component
 */
@Configuration // 变为配置类
@ComponentScan("com.lh.pojo") // 扫描包，相当于之前的 <context:component-scan>
@Import(MyConfig2.class) // Import导入另外一个配置文件，可以同时使用
public class MyConfig {

    /**
     * 注册一个Bean, 相当于之前配置文件中的bean标签
     *     这个方法的名字，就相当于bean标签中的id属性
     *     这个方法的返回值，就相当于bean标签中的class属性
     */
    @Bean
    public User getUser(){
        return new User(); // 就是返回要注入到bean的对象
    }

}
