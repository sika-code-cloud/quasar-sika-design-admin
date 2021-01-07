package com.quasar.sika.design.server.business.config.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 参数配置表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ConfigQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long configId;
    /**
     * 参数名称
     */
    protected String configName;
    /**
     * 参数键名
     */
    protected String configKey;
    /**
     * 参数键值
     */
    protected String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    protected String configType;
    /**
     * id列表
     */
    protected Set<Long> ids;

}

