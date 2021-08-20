package com.sika.code.standard.db.algorithm.table;

import cn.hutool.core.util.StrUtil;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.standard.db.properties.CustomerShardingProperties;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @author sikadai
 * @Description: 基础分片算法
 * @date 2021/7/415:17
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseTableShardingAlgorithm<T extends Comparable<?>> implements PreciseShardingAlgorithm<T> {
    private String dataBaseNameExpression;
    private String tableNameExpression;
    protected static final char FILLED_CHAR_ZERO = '0';
    protected static final int LENGTH_FOUR = 4;
    protected static final int LENGTH_TWO = 2;

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<T> shardingValue) {
        String dataName = getDataSourceName(availableTargetNames, shardingValue);
        String tableName = getTableName(availableTargetNames, shardingValue);
        return StrUtil.join(StrUtil.DOT, dataName, tableName);
    }

    /**
     * 获取数据库名称-不是数据源的名称
     */
    protected abstract String getDataSourceName(Collection<String> availableTargetNames, PreciseShardingValue<T> shardingValue);

    /**
     * 获取表名称
     */
    protected abstract String getTableName(Collection<String> availableTargetNames, PreciseShardingValue<T> shardingValue);

    public String getDataBaseNameExpression(String logicTableName) {
        if (StrUtil.isNotBlank(this.dataBaseNameExpression)) {
            return this.dataBaseNameExpression;
        }
        this.dataBaseNameExpression = SpringUtil.getBean(CustomerShardingProperties.class).getShardingItemMap().get(logicTableName).getDataBaseNameExpression();
        return this.dataBaseNameExpression;
    }

    public String getTableNameExpression(String logicTableName) {
        if (StrUtil.isNotBlank(this.tableNameExpression)) {
            return this.tableNameExpression;
        }
        this.tableNameExpression = SpringUtil.getBean(CustomerShardingProperties.class).getShardingItemMap().get(logicTableName).getTableNameExpression();
        return this.tableNameExpression;
    }
}
