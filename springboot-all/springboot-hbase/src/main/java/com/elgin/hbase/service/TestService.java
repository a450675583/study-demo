package com.elgin.hbase.service;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zxs
 * 2020/8/7
 */
@RestController
public class TestService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Value("${spring.data.hbase.quorum}")
    private String zk;

    @RequestMapping("/createTable")
    public Object addUser() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", zk);
        //建立连接
        Connection conn = ConnectionFactory.createConnection(conf);
        //获取表的管理类
        Admin admin = conn.getAdmin();
        //定义表
        HTableDescriptor hTableDescriptor=new HTableDescriptor(TableName.valueOf("person"));
        //定义列簇
        HColumnDescriptor hColumnDescriptor =new HColumnDescriptor("info");
        //讲列簇定义到表中
        hTableDescriptor.addFamily(hColumnDescriptor);
        //执行建表操作
        admin.createTable(hTableDescriptor);
        admin.close();
        conn.close();
        return null;
    }


}
