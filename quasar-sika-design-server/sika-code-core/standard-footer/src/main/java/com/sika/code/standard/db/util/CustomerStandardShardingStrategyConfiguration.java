package com.sika.code.standard.db.util;

import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sikadai
 * @Description:
 * @date 2021/7/412:42
 */
@Getter
@AllArgsConstructor
public class CustomerStandardShardingStrategyConfiguration  implements ShardingStrategyConfiguration {
    private final String shardingColumn;
    private final String preciseAlgorithmClassName;
    @Override
    public ShardingStrategy build() {
        return new StandardShardingStrategyConfiguration(shardingColumn, preciseAlgorithmClassName).build();
    }
}
