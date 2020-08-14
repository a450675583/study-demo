package com.elgin.hbase.client.service;

import com.elgin.hbase.client.config.HBaseClient;
import com.elgin.hbase.client.config.HbaseCell;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zxs
 * 2020/8/13
 */
@RestController
public class HbaseTestService {

    @Autowired
    private HBaseClient hBaseClient;

    @GetMapping("test")
    public Object test() throws IOException {
        //建表
        //hBaseClient.createTable("bbs_user_follow_tag", Lists.newArrayList("follow_tag"));
        //写数据
        hBaseClient.insertOrUpdate("bbs_user_follow_tag","bbs_puid_0051598500","follow_tag","110","1");
        //读数据
        HbaseCell result = hBaseClient.selectOneRow("bbs_user_follow_tag","bbs_puid_0051598500");
        return result;
    }
}
