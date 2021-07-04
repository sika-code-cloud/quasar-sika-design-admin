package com.sika.code.standard.db.algorithm.table;

import cn.hutool.core.util.StrUtil;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;

import java.util.Collection;

/**
 * @author sikadai
 * @Description:百库十表分片算法
 * @date 2021/7/415:16
 */
public class HundredTenShardingAlgorithm extends BaseTableShardingAlgorithm<Long> {

    @Override
    protected String getDataSourceName(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String hundred = StrUtil.fillBefore(String.valueOf(shardingValue.getValue() % 1000 / 10), FILLED_CHAR_ZERO, LENGTH_TWO);
        return StrUtil.format(getDataBaseNameExpression(shardingValue.getLogicTableName()), hundred);
    }

    @Override
    protected String getTableName(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String ten = String.valueOf(shardingValue.getValue() % 10);
        return StrUtil.format(getTableNameExpression(shardingValue.getLogicTableName()), ten);
    }
}

