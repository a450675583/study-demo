package com.elgin.hbase.client.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxs
 * 2020/8/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HbaseCell {

    private String rowKey;

    private String column;

    private String value;

    private String columnFamily;
}
