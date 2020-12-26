package com.quasar.sika.design.server.business.rolemenu.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.rolemenu.entity.RoleMenuEntity;
import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;

/**
 * <p>
 * 角色菜单表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:47
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMenuConvert extends BaseConvert<RoleMenuEntity, RoleMenuDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    RoleMenuConvert INSTANCE = Mappers.getMapper(RoleMenuConvert.class);

}
