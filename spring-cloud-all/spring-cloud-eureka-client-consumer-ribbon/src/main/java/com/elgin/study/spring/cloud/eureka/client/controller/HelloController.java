package com.elgin.study.spring.cloud.eureka.client.controller;

import com.elgin.study.spring.cloud.eureka.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return  helloService.hi(name);
    }


    @RequestMapping("/home")
    public String home(){
        return  helloService.home();
    }
}
