package com.lh.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Value("${person.last-name}")
    private String name;

    @ResponseBody // return写回给浏览器
    @RequestMapping("/sayHello")
    public String sayHello(){
       return "Hello " + name;
    }
}
