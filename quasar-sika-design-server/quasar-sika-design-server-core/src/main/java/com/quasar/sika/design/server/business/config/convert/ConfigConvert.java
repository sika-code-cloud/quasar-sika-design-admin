package com.quasar.sika.design.server.business.config.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.config.entity.ConfigEntity;
import com.quasar.sika.design.server.business.config.pojo.dto.ConfigDTO;

/**
 * <p>
 * 参数配置表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:07
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigConvert extends BaseConvert<ConfigEntity, ConfigDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ConfigConvert INSTANCE = Mappers.getMapper(ConfigConvert.class);

}
