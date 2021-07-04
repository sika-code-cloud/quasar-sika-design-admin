package com.sika.code.standard.db.util;

import com.sika.code.common.json.util.JSONUtil;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * TODO 尚未完整
 * </p>
 * <pre>
 *     这个不是查询分片算法-是匹配数据源的算法
 * </pre>
 *
 * @author daiqi
 * @date 2018/12/4 9:40
 */
@Slf4j
public class HundredDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
//        for (String each : collection) {
//            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
        String logicTableName = preciseShardingValue.getLogicTableName();
        String columnNam = preciseShardingValue.getColumnName();
        Object value = preciseShardingValue.getValue();
        log.info("collection:{}, getLogicTableName：{},getColumnName:{},getValue:{}",
                JSONUtil.toJSONString(collection),
                logicTableName, columnNam, value);
//        String dataBase = "test_" + StrUtil.fillBefore(String.valueOf(value / 10), '0', 2) + "_db";
        String dataBase = "test_db";
        return dataBase;
    }


    //public class DemoDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
//
//    @Override
//    public String doEqualSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//
//        for (String each : databaseNames) {
//            if (each.endsWith(Long.parseLong(shardingValue.getValue().toString()) % 2 + "")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public Collection<String> doInSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
//        for (Long value : shardingValue.getValues()) {
//            for (String tableName : databaseNames) {
//                if (tableName.endsWith(value % 2 + "")) {
//                    result.add(tableName);
//                }
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
//        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
//        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : databaseNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//    }
} 