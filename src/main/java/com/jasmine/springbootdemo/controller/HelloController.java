package com.jasmine.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/")
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

}
