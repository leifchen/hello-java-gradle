package com.chen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello控制器
 * <p>
 * @Author LeifChen
 * @Date 2019-04-23
 */
@RestController
@RequestMapping("/spring")
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello Spring MVC";
    }
}
