package com.elgin.study.spring.circular.reference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 循环依赖测试
 */
public class MainClass {

    public static void main(String[] args) {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("circularRef/application.xml");
        ClassA a =applicationContext.getBean(ClassA.class);
        ClassB b =applicationContext.getBean(ClassB.class);
        a.classa();
        b.classb();

    }
}
