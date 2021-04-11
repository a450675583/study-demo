package com.elgin.hbase.client.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;

/**
 * @author zxs
 * 2020/8/13
 */
@Configuration
@EnableConfigurationProperties(HbaseProperties.class)
public class HbaseConfig {

    private final HbaseProperties properties;

    public HbaseConfig(HbaseProperties properties){
        this.properties = properties;
    }

    public org.apache.hadoop.conf.Configuration configuration(){
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        Map<String,String> config = properties.getConfig();
        Set<String> keySet = config.keySet();
        for(String key :keySet){
            configuration.set(key,config.get(key));
        }
        return configuration;
    }
}
