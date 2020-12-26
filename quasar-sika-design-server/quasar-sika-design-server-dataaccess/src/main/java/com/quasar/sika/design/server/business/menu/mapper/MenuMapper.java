package com.quasar.sika.design.server.business.menu.mapper;

import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:27
 */
@Repository
public interface MenuMapper extends BaseStandardMapper<MenuEntity> {
    List<MenuEntity> listMenuByRoleId(Long roleId);
}
