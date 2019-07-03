package com.elgin.study.spring.cloud.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class SpringCloudEurekaClientProvider02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaClientProvider02Application.class, args);
    }

    @Value("${server.port}")
    private String port;

    @RequestMapping("/home")
    public String home(){
        return  "Hello World ! " + "|| port = " + port ;
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return  "Hello " + name + " || port= " + port;
    }

}
