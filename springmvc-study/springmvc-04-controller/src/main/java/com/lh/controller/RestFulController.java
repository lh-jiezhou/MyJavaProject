package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    // 原来的风格: http://localhost:8080/add?a=1&b=2
    // RestFul风格: http://localhost:8080/add/1/2

    @RequestMapping("/add")
    public String test1(int a, int b, Model model){
        int res = a + b;
        model.addAttribute("msg", "结果为"+res);
        return "test1";
    }

//    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
    @RequestMapping("/add/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model){
        int res = a + b;
        model.addAttribute("msg", "结果为"+res);
        return "test1";
    }

//    相同Url, 不同处理
//    @GetMapping("/add/{a}")
//    ...
//
//    @PostMapping("/add/{a}")
//    ...

}
