package com.quasar.sika.design.server.business.operlog.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.operlog.entity.OperLogEntity;
import com.quasar.sika.design.server.business.operlog.pojo.dto.OperLogDTO;

/**
 * <p>
 * 操作日志记录 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:35
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperLogConvert extends BaseConvert<OperLogEntity, OperLogDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    OperLogConvert INSTANCE = Mappers.getMapper(OperLogConvert.class);

}
