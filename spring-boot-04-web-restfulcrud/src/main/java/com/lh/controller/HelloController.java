package com.lh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 返回json数据，不被视图解析器处理
public class HelloController {

    @GetMapping("/hello") // RestFul 风格
    public String hello(){
        return "hello,world";
    }
}
