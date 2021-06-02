package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 表示这个类会被Spring接管
// 被这个注解的类中所有的方法，如果返回值是String,
// 并且有具体的页面可以跳转，那么就会被视图解析器解析
@Controller
public class ControllerTest2 {

    @RequestMapping("/test2")
    public String test2(Model model){
        model.addAttribute("msg", "ControllerTest2");
        return "test1"; // 页面复用
    }

    @RequestMapping("/test3")
    public String test3(Model model){
        model.addAttribute("msg", "滴滴滴");
        return "test1"; // 页面复用
    }
}
