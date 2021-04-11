package com.elgin.hbase.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author zxs
 * 2020/8/13
 */
@Data
@ConfigurationProperties(prefix = "hbase")
public class HbaseProperties {

    private Map<String,String> config;

}
