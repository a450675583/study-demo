package com.elgin.study.spring.cloud.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client-provider")
public interface IHelloService {

    @RequestMapping("/home")
    String home();

    @RequestMapping("/hi")
    String sayHi(@RequestParam("name") String name);
}
