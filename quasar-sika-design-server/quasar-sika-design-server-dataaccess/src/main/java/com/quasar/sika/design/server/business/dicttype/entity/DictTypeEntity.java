package com.quasar.sika.design.server.business.dicttype.entity;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sika_dict_type")
public class DictTypeEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;


}
