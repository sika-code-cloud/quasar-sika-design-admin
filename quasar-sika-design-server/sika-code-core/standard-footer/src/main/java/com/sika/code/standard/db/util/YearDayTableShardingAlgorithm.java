package com.sika.code.standard.db.util;

import com.sika.code.common.date.util.DateUtil;
import com.sika.code.common.json.util.JSONUtil;
import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Date;

/**
 * TODO 尚未完整
 * <p>
 * 年库天表分片算法-该算法才是真正重写数据库.表名的算法
 * </p>
 *
 * @author daiqi
 * @date 2018/11/29 18:02
 */
@Slf4j
public class YearDayTableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    private static final char FILLED_CHAR = '0';
    private static final int LENGTH_FOUR = 4;
    private static final int LENGTH_TWO = 2;
    private String dataSourceNameExpression;
    private String tableNameExpression;
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
//        for (String each : collection) {
//            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2 + "")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
        String logicTableName = preciseShardingValue.getLogicTableName();
        String columnNam = preciseShardingValue.getColumnName();
        Date value = preciseShardingValue.getValue();
        log.info("collection:{}, getLogicTableName：{},getColumnName:{},getValue:{}",
                JSONUtil.toJSONString(availableTargetNames),
                logicTableName, columnNam, DateUtil.formatDate(value));

        String yearStr = DateUtil.format(value, "yyyy");
        String monthDayStr = DateUtil.format(value, "MMdd");
        log.info("yearStr:{},monthDayStr:{},day:{}", yearStr, monthDayStr);
        String tableName = "test_" + yearStr + "_db" + "." +  logicTableName + "_" + monthDayStr;
        return tableName;
    }

    private String buildYearDayTableName(Collection<String> availableTargetNames, PreciseShardingValue<Date> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        String columnNam = preciseShardingValue.getColumnName();
        Date value = preciseShardingValue.getValue();
        String yearStr = DateUtil.format(value, "yyyy");
        String monthDayStr = DateUtil.format(value, "MMdd");
        return  "test_" + yearStr + "_db" + "." +  logicTableName + "_" + monthDayStr;
    }

    //    private static Long timeNode1 = 13L;
//
//    /**
//     * select * from t_order where user_id = 11；类似这个意思
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        for (String each : tableNames) {
//            Long currentTime = shardingValue.getValue()>>23;
//            if (currentTime<=timeNode1){
//                if (each.endsWith(shardingValue.getValue() % 2 + "")) {
//                    return each;
//                }
//            }else {
//                if (each.endsWith(shardingValue.getValue() % 2 + "_1")) {
//                    return each;
//                }
//            }
//        }
//        throw new IllegalArgumentException();
//
//    }
//
//    /**
//     * where user_id in (1,23,7)
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        for (Long value : shardingValue.getValues()) {
//            for (String tableName : tableNames) {
//                if (tableName.endsWith(value % 2 + "")) {
//                    result.add(tableName);
//                }
//            }
//        }
//        return result;
//
//    }
//
//    /**
//     * where user_id between(1, 6)
//     *
//     * @param tableNames
//     * @param shardingValue
//     * @return
//     */
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
//        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : tableNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
//
//    }
}