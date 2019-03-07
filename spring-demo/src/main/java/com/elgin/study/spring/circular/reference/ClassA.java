package com.elgin.study.spring.circular.reference;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClassA {


    @Autowired
    private ClassB classB;


    public void  classa(){
        log.info("invoke classa");
    }

}
