package com.sika.code.standard.db.algorithm.table;

import cn.hutool.core.util.StrUtil;
import com.sika.code.common.date.constant.DateConstant;
import com.sika.code.common.date.util.DateUtil;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @author sikadai
 * @Description:年库月表分片算法
 * @date 2021/7/415:16
 */
public class YearMonthShardingAlgorithm extends BaseTableShardingAlgorithm<Date> {

    @Override
    protected String getDataSourceName(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        String year = DateUtil.format(shardingValue.getValue(), DateConstant.YEAR);
        return StrUtil.format(getDataBaseNameExpression(shardingValue.getLogicTableName()), year);
    }

    @Override
    protected String getTableName(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        String monthAndDay = DateUtil.format(shardingValue.getValue(), DateConstant.MONTH);
        return StrUtil.format(getTableNameExpression(shardingValue.getLogicTableName()), monthAndDay);
    }
}
