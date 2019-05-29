package com.elgin.study.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{

    @RequestMapping("/hello")
    String hello() {
        return "Hello World!";
    }


    @RequestMapping("/exceptionTest")
    String exception() {
        throw new RuntimeException("exception test");
    }

}