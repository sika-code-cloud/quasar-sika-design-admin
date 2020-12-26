package com.quasar.sika.design.server.business.menu.service;

import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:28
 */
public interface MenuService extends BaseStandardService<MenuDTO> {
    List<MenuDTO> listMenuByRoleId(Long roleId);
}
