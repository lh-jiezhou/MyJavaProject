package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转发与重定向
 *    测试前将 springmvc-servlrt.xml中的视图解析配置文件注释掉
 *
 *    有视图解析器的情况
 *      return 默认 为转发
 *      return "redirect:/index.jsp" 为重定向
 */
@Controller
public class ModelTest1 {

    @RequestMapping("m1/t1")
    public String test1(Model model){

        model.addAttribute("msg", "ModelTest1");
//        return "test1";
        // 转发 (转发可以访问 WEB-INF 目录下的 jsp)
//        return "WEB-INF/jsp/test1.jsp";

        // 转发2
//        return "forward:/index.jsp";

        // 重定向 (重定向可以访问 WEB-INF 目录下的 jsp)
        return "redirect:/index.jsp";
    }




}
