package com.jasmine.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello Controller
 *
 * @author xieshanghan
 * @version HelloController.java, v 0.1, 2023年01月04日 10:45 xieshanghan
 */
@Controller
@RequestMapping("/api/v1/")
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

}