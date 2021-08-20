package com.sika.code.standard.db.config;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.sika.code.standard.db.properties.CustomerShardingProperties;
import com.sika.code.standard.db.properties.ShardingItem;
import com.sika.code.standard.db.util.CustomerStandardShardingStrategyConfiguration;
import com.sika.code.standard.db.util.DataSourceKey;
import com.sika.code.standard.db.util.DynamicDataSource;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.NoneShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * TODO 尚未完整
 * 分表分库基础设置
 * </p>
 * <pre>
 *     在设置了spring.datasource.SHARDING.enable 等于true是开启分表分库
 * </pre>
 *
 * @author daiqi
 * @date 2018/12/4 9:38
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
/** 排除DataSourceConfiguratrion */
@ConditionalOnProperty(name = {"spring.datasource.sharding.enable"}, matchIfMissing = false, havingValue = "true")
public class ShardingDataSourceConfig {
    @Autowired
    private CustomerShardingProperties customerShardingProperties;

    // 创建数据源
    // 所有引入db-core的模块都需要一个核心库，可以是user-center，也可以是oauth-center,file-center,sms-center
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.master")
    public DataSource dataSourceMaster() {
        return DataSourceBuilder.create().build();
    }

    // 从数据
    @Bean
    @ConfigurationProperties("spring.datasource.hikari.slave")
    public DataSource dataSourceSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari.single")
    public DataSource dataSourceSingle() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardingDataSource")
    public DataSource shardingDataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster) throws SQLException {
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put("dataSourceMaster", dataSourceMaster);
        dataSourceMap.put("dataSourceMaster1", dataSourceMaster);
        return buildDataSource(dataSourceMap);
    }

    private void buildDefaultDataSourceConfig(ShardingRuleConfiguration shardingRuleConfig) {
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        shardingRuleConfig.setDefaultDataSourceName(customerShardingProperties.getDefaultShardingItem().getDataSourceName());
    }

    public void buildSharingTableRuleConfiguration(ShardingRuleConfiguration shardingRuleConfig) {
        Map<String, ShardingItem> shardingItemMap = customerShardingProperties.getShardingItemMap();
        if (CollUtil.isEmpty(shardingItemMap)) {
            return;
        }
        Collection<TableRuleConfiguration> tableRuleConfigurations = shardingRuleConfig.getTableRuleConfigs();
        for (Map.Entry<String, ShardingItem> entry : shardingItemMap.entrySet()) {
            String logicTableName = entry.getKey();

            ShardingItem shardingItem = entry.getValue();
            String shardingColumn = shardingItem.getShardingColumn();
            ShardingStrategyConfiguration dataConfiguration = new CustomerStandardShardingStrategyConfiguration(shardingColumn, shardingItem.getDataSourceAlgorithmClassName());
            ShardingStrategyConfiguration tableConfiguration = new CustomerStandardShardingStrategyConfiguration(shardingColumn, shardingItem.getTableAlgorithmClassName());

            TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
            tableRuleConfiguration.setLogicTable(logicTableName);
            tableRuleConfiguration.setDatabaseShardingStrategyConfig(dataConfiguration);
            tableRuleConfiguration.setTableShardingStrategyConfig(tableConfiguration);

            tableRuleConfigurations.add(tableRuleConfiguration);
        }
    }

    private DataSource buildDataSource(Map<String, DataSource> dataSourceMap) {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 构建默认的数据源配置
        buildDefaultDataSourceConfig(shardingRuleConfig);
        // 构建分片规则
        buildSharingTableRuleConfiguration(shardingRuleConfig);
        // prop
        Properties prop = customerShardingProperties.getProperties();
        Map<String, Object> configMap = customerShardingProperties.getConfigMap();
        try {
            return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, configMap, prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean // 只需要纳入动态数据源到spring容器
    @Primary
    public DataSource dataSource(@Qualifier("shardingDataSource") DataSource shardingDataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        DataSource coreDataSource = this.dataSourceSingle();
        dataSource.addDataSource(DataSourceKey.CORE, shardingDataSource);
//        dataSource.addDataSource(DataSourceKey.CORE, coreDataSource);
        dataSource.setDefaultTargetDataSource(shardingDataSource);
        return dataSource;
    }
    
    /**
     * 需要手动配置事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
