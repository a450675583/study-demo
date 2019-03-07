package com.elgin.study.spring.bean.lifecycle;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class LifeCycleTestBean implements InitializingBean,DisposableBean,ApplicationContextAware {


    public LifeCycleTestBean(){
        log.info("invoke LifeCycleTestBean() construct method...");
    }

    @PostConstruct
    public void postConstruct(){
        log.info("invoke annotations with @PostConstruct method...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("invoke annotations with @PreDestroy method...");
    }

    @Override
    public void destroy() throws Exception {
        log.info("invoke destory method...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("invoke afterPropertiesSet method...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("invoke setApplicationContext method...");
    }

    public void initMethod(){
        log.info("invoke initMethod method...");
    }

    public void destoryMethod(){
        log.info("invoke destoryMethod method...");
    }

}
