package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody // return写回给浏览器
    @RequestMapping("/hello") // 接受来自浏览器的请求
    public String hello(){
        return "Hello World";
    }
}
