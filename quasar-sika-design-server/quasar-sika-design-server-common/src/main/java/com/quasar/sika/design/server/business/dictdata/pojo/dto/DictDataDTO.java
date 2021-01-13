package com.quasar.sika.design.server.business.dictdata.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DictDataDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long dictDataId;
    /**
     * 字典排序
     */
    private Integer dictSort;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典键值
     */
    private String dictValue;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;
    /**
     * 表格回显样式
     */
    private String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    private String isDefault;

}
