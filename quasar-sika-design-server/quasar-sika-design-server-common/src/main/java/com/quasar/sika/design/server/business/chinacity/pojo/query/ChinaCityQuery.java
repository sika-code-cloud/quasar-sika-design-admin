package com.quasar.sika.design.server.business.chinacity.pojo.query;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.sika.code.standard.base.pojo.query.BaseStandardQuery;

/**
 * <p>
 * 参数配置表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ChinaCityQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long chinaCityId;
    /**
     * 城市名称
     */
    protected String cityName;
    /**
     * 城市编码
     */
    protected String code;
    /**
     * 父编码
     */
    protected String parentCode;
    /**
     * id列表
     */
    protected Set<Long> ids;

}

