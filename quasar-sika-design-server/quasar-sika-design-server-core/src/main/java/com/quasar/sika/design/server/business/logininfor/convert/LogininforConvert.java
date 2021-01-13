package com.quasar.sika.design.server.business.logininfor.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.logininfor.entity.LogininforEntity;
import com.quasar.sika.design.server.business.logininfor.pojo.dto.LogininforDTO;

/**
 * <p>
 * 系统访问记录 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:00
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogininforConvert extends BaseConvert<LogininforEntity, LogininforDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    LogininforConvert INSTANCE = Mappers.getMapper(LogininforConvert.class);

}
