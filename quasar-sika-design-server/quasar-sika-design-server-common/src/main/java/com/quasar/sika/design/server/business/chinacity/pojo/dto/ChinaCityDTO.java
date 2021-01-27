package com.quasar.sika.design.server.business.chinacity.pojo.dto;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ChinaCityDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long chinaCityId;
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
