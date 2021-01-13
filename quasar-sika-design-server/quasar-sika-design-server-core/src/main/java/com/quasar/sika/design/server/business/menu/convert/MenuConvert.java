package com.quasar.sika.design.server.business.menu.convert;

import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;

/**
 * <p>
 * 菜单权限表 转换类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConvert extends BaseConvert<MenuEntity, MenuDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

}
