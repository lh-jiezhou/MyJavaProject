package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    // 首页; 不建议此方式； 建议在config中配置
//    @RequestMapping({"/", "/index.html"})
//    public String index(){
//        return "index";
//    }
}
