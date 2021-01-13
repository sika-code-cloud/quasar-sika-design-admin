package com.quasar.sika.design.server.business.dicttype.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.dicttype.entity.DictTypeEntity;
import com.quasar.sika.design.server.business.dicttype.pojo.dto.DictTypeDTO;

/**
 * <p>
 * 字典类型表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:48
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictTypeConvert extends BaseConvert<DictTypeEntity, DictTypeDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

}
