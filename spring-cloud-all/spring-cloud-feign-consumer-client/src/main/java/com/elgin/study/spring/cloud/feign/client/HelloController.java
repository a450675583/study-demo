package com.elgin.study.spring.cloud.feign.client;

import com.elgin.study.spring.cloud.feign.client.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return  helloService.sayHi(name);
    }


    @RequestMapping("/home")
    public String home(){
        return  helloService.home();
    }


}
