package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController // 返回json数据，不被视图解析器处理
public class HelloController {

    // 接口: http://localhost:8080/hello
    @RequestMapping("/hello") // 接受来自浏览器的请求
    public String hello(){
        return "Hello World";
    }
}

//@Controller
//public class HelloController {
//
//    @ResponseBody // return写回给浏览器,不加则会返回视图,会被视图解析器处理
//    @RequestMapping("/hello") // 接受来自浏览器的请求
//    public String hello(){
//        return "Hello World";
//    }
//}
