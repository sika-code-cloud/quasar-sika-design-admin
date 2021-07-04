package com.sika.code.standard.db.properties;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.sika.code.standard.db.algorithm.datasource.DefaultDataSourceShardingAlgorithm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

/**
 * @author sikadai
 * @Description: 分片属性配置
 * @date 2021/7/417:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.datasource.sharding")
public class CustomerShardingProperties {
    private Properties properties = new Properties();
    private ShardingItem defaultShardingItem;
    private Map<String, ShardingItem> shardingItemMap = Maps.newLinkedHashMap();
    private Map<String, Object> configMap = Maps.newHashMap();

    @PostConstruct
    public void build() {
        buildShardingItemMap();
    }

    protected void buildShardingItemMap() {
        if (CollUtil.isEmpty(shardingItemMap)) {
            return;
        }
        for (Map.Entry<String, ShardingItem> entry : shardingItemMap.entrySet()) {
            ShardingItem shardingItem = entry.getValue();
            String logicTableName = entry.getKey();
            if (StrUtil.isBlank(shardingItem.getDataSourceName())) {
                shardingItem.setDataSourceName(defaultShardingItem.getDataSourceName());
            }
            if (StrUtil.isBlank(shardingItem.getDataSourceAlgorithmClassName())) {
                shardingItem.setDataSourceAlgorithmClassName(DefaultDataSourceShardingAlgorithm.class.getName());
                DefaultDataSourceShardingAlgorithm.addTableToDbSource(logicTableName, shardingItem.getDataSourceName());;
            }
        }
    }
}
