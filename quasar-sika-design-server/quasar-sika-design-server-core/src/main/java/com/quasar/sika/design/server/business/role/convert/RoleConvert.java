package com.quasar.sika.design.server.business.role.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.role.entity.RoleEntity;
import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;

/**
 * <p>
 * 角色信息表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:58
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConvert extends BaseConvert<RoleEntity, RoleDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

}
