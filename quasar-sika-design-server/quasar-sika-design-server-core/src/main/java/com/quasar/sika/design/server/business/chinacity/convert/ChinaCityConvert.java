package com.quasar.sika.design.server.business.chinacity.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.chinacity.entity.ChinaCityEntity;
import com.quasar.sika.design.server.business.chinacity.pojo.dto.ChinaCityDTO;

/**
 * <p>
 * 参数配置表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:42
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChinaCityConvert extends BaseConvert<ChinaCityEntity, ChinaCityDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ChinaCityConvert INSTANCE = Mappers.getMapper(ChinaCityConvert.class);

}
