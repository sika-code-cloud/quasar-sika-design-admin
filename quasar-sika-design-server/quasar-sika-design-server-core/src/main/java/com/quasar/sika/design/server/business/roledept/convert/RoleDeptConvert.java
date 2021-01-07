package com.quasar.sika.design.server.business.roledept.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.roledept.entity.RoleDeptEntity;
import com.quasar.sika.design.server.business.roledept.pojo.dto.RoleDeptDTO;

/**
 * <p>
 * 角色和部门关联表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:12
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDeptConvert extends BaseConvert<RoleDeptEntity, RoleDeptDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    RoleDeptConvert INSTANCE = Mappers.getMapper(RoleDeptConvert.class);

}
