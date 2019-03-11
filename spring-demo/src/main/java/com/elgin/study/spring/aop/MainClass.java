package com.elgin.study.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

    public static void main(String[] args) {
        ApplicationContext ctx =new ClassPathXmlApplicationContext("aop/application.xml");
        SomeService someService = ctx.getBean(SomeService.class);
        someService.someMethod1();
        someService.someMethod2();
    }
}
