package com.elgin.study.springboot.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyArgumentBean {

    @Autowired
    public MyArgumentBean(ApplicationArguments args){
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        log.info("ApplicationArguments 中的args：{}",files);
        log.info("ApplicationArguments containsOption debug：{}",debug);
    }
}
