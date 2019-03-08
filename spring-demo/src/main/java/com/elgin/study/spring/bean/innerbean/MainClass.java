package com.elgin.study.spring.bean.innerbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1.在<property>或<constructor-arg>内部通过<bean>元素定义的，
 *
 * 2.该bean不管是否指定id或者name，该bean都有一个唯一的匿名标识符，且不能被指定别名
 *
 * 3.该bean队其他外部的bean不可见。
 */
public class MainClass {

    public static void main(String[] args) {
        ApplicationContext ctx =new ClassPathXmlApplicationContext("innerbean/application.xml");
        Student student=ctx.getBean(Student.class);

        //No qualifying bean of type 'com.elgin.study.spring.bean.innerbean.Student$Person' available
        //内部bean不可见
        Student.Person person=ctx.getBean(Student.Person.class);


        System.out.println(student);
        System.out.println(person);



    }
}
