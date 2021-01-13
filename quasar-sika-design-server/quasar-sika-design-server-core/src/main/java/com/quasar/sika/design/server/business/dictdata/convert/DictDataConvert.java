package com.quasar.sika.design.server.business.dictdata.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.dictdata.entity.DictDataEntity;
import com.quasar.sika.design.server.business.dictdata.pojo.dto.DictDataDTO;

/**
 * <p>
 * 字典数据表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDataConvert extends BaseConvert<DictDataEntity, DictDataDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

}
