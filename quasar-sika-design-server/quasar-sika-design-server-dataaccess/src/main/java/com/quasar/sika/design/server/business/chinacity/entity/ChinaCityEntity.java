package com.quasar.sika.design.server.business.chinacity.entity;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sika_china_city")
public class ChinaCityEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市编码
     */
    private String code;

    /**
     * 父编码
     */
    private String parentCode;


}
