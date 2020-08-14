package com.elgin.hbase.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class HbaseJavaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbaseJavaClientApplication.class, args);
    }

}
