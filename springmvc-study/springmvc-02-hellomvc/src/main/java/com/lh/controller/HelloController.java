package com.lh.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 实现 Controller 接口
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();
        // 封装对象，放在ModelAndView中，Model
        String result = "HelloSpringMVC";
        mv.addObject("msg", result);
        // 封装要跳转的视图，放在ModelView中
        mv.setViewName("hello"); // 由于springmvc-servlet.xml配置
        // 完整路径 /WEB-INF/jsp/hello.jsp

        return mv;
    }

}
