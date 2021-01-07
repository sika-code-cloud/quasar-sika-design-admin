package com.quasar.sika.design.server.business.dicttype.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 字典类型表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DictTypeQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long dictTypeId;
    /**
     * 字典名称
     */
    protected String dictName;
    /**
     * 字典类型
     */
    protected String dictType;
    /**
     * id列表
     */
    protected Set<Long> ids;

}

