package com.elgin.study.spring.circular.reference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClassB {

    @Autowired
    private ClassA classA;

    public void  classb(){
        log.info("invoke classb");
    }
}
