package com.elgin.study.spring.bean.innerbean;

import lombok.Data;

@Data
public class Student {

    private  Person  person;

    @Data
    public class Person{
        private String name;
        private String address;

    }
}
