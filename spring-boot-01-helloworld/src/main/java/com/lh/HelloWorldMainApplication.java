package com.lh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 *本身是一个spring组件
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    // 快捷键 psvm
    public static void main(String[] args) {

        // Spring 应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);

    }
}
