package com.elgin.study.springboot.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLineBean implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineBean run :{}" ,args);
    }
}
