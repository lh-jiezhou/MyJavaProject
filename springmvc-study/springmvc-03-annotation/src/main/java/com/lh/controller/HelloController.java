package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController 返回json格式数据, 不会被视图解析器解析
@Controller
@RequestMapping("/hello")

public class HelloController {

    @RequestMapping("/h1")
    public String hello1(Model model){
        String result = "Hello, SpringMVC Annotation";
        model.addAttribute("msg", result); // 封装数据
        return "hello"; // 返回即视图，会被视图解析器处理
    }

    @RequestMapping("/h2")
    public String hello2(Model model){
        String result = "Hello, SpringMVC Annotation";
        model.addAttribute("msg", result); // 封装数据
        return "hello"; // 返回即视图，会被视图解析器处理
    }

    @RequestMapping("/h3")
    public String hello3(Model model){
        String result = "Hello, SpringMVC Annotation";
        model.addAttribute("msg", result); // 封装数据
        return "hello"; // 返回即视图，会被视图解析器处理
    }
}
