package com.elgin.study.spring.cloud.feign.client.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceHystrix implements IHelloService {

    @Override
    public String home() {
        return null;
    }

    @Override
    public String sayHi(String name) {
        return null;
    }
}
