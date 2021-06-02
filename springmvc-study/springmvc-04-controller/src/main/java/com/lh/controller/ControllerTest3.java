package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/c3")
public class ControllerTest3 {

    @RequestMapping("/test1")
    public String test1(Model model){
        Map<Integer, char[]> map = new HashMap<>();
        map.put(2, new char[]{'a','b','c'});
        return "test1";
    }
}
