package com.lh.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 只要实现了 Controller 接口的类，说明这就是一个控制器了
public class ControllerTest1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "ControllerTest1");
        mv.setViewName("test1"); // jsp视图
        return mv;
    }
}
