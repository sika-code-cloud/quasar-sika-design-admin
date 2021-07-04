package com.sika.code.standard.db.properties;

import lombok.Data;

/**
 * @author sikadai
 * @Description: 分片Item
 * @date 2021/7/417:59
 */
@Data
public class ShardingItem {
    private String shardingColumn;
    private String dataSourceName;
    private String dataBaseNameExpression;
    private String tableNameExpression;
    private String dataSourceAlgorithmClassName;
    private String tableAlgorithmClassName;
}
