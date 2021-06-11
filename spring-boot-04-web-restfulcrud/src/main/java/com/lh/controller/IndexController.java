package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * 在templates目录下的所有页面，只能通过controller来跳转(类似WEB-INF目录)
 */

@Controller // 跳转页面,交由视图解析器
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model) {
        model.addAttribute("msg", "<h1>thymeleaf model</h1>");

        model.addAttribute("users", Arrays.asList("lihao", "jiezhou"));
        return "test";
    }

}
