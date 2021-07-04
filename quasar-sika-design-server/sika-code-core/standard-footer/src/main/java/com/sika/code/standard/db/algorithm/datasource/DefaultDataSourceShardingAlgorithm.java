package com.sika.code.standard.db.algorithm.datasource;

import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 基础数据源分片算法
 * </p>
 * <pre>
 *     这个不是查询分片算法-是匹配数据源的算法
 * </pre>
 *
 * @author daiqi
 * @date 2018/12/4 9:40
 */
@Slf4j
public class DefaultDataSourceShardingAlgorithm implements PreciseShardingAlgorithm<Comparable<?>> {
    private static final Map<String, String> TABLE_TO_DB_SOURCE = Maps.newConcurrentMap();

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Comparable<?>> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        String dbSource = TABLE_TO_DB_SOURCE.get(logicTableName);
        if (!availableTargetNames.contains(dbSource)) {
            throw new IllegalArgumentException();
        }
        return dbSource;
    }

    public static void addTableToDbSource(String logicTableName, String dbSource) {
        TABLE_TO_DB_SOURCE.put(logicTableName, dbSource);
    }
}