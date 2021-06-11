package com.lh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 原始方法
 *
 */
@Configuration // 如果要扩展springmvc 官方建议 使用 @Configuration注解
//@EnableWebMvc // 不能加该注解; 此注解导入了一个人DelegatingWebMvcConfiguration类: 从容器中获取所有的webmvcConfig
public class MyMvcConfig2 implements WebMvcConfigurer {

    // 视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/lh").setViewName("test");
    }
}
