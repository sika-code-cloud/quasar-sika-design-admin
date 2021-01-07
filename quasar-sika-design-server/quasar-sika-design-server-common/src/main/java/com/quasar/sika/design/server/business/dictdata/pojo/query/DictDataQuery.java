package com.quasar.sika.design.server.business.dictdata.pojo.query;

import com.sika.code.standard.base.pojo.query.BaseStandardQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 字典数据表 查询类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DictDataQuery extends BaseStandardQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据表id
     */
    protected Long dictDataId;
    /**
     * 字典排序
     */
    protected Integer dictSort;
    /**
     * 字典标签
     */
    protected String dictLabel;
    /**
     * 字典键值
     */
    protected String dictValue;
    /**
     * 字典类型
     */
    protected String dictType;
    /**
     * 样式属性（其他样式扩展）
     */
    protected String cssClass;
    /**
     * 表格回显样式
     */
    protected String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    protected String isDefault;
    /**
     * id列表
     */
    protected Set<Long> ids;

}

