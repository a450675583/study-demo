package com.elgin.study.spring.cloud.eureka.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    public String home(){
        return restTemplate.getForObject("http://CLIENT-PROVIDER/home",String.class);
    }


    public String hi(String name){
        return restTemplate.getForObject("http://CLIENT-PROVIDER/hi?name=" + name,String.class);
    }


}
