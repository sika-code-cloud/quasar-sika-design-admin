package com.quasar.sika.design.server.business.rolemenu.service;

import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:45
 */
public interface RoleMenuService extends BaseStandardService<RoleMenuDTO> {
    List<RoleMenuDTO> listByRoleId(Long roleId);

    List<RoleMenuDTO> listByRoleIds(Set<Long> roleIds);

    List<RoleMenuDTO> listByMenuId(Long menuId);

    List<RoleMenuDTO> listByMenuIds(Set<Long> menuIds);

    Set<Long> listRoleIdsByMenuId(Long menuId);

    Set<Long> listRoleIdsByMenuIds(Set<Long> menuIds);

    Set<Long> listMenuIdsByRoleId(Long roleId);

    Set<Long> listMenuIdsByRoleIds(Set<Long> roleIds);
}
