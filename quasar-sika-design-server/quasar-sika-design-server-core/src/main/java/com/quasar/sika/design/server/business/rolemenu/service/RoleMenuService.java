package com.quasar.sika.design.server.business.rolemenu.service;

import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.Set;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:21
 */
public interface RoleMenuService extends BaseStandardService<RoleMenuDTO> {
    /**
     * <p>
     * 根据角色id获取菜单id列表
     * </p>
     *
     * @param roleId
     * @return java.util.Set<java.lang.Long>
     * @author daiqi
     * @date 2021/1/8 0:08
     */
    Set<Long> listMenuIdsByRoleId(Long roleId);

    /**
     * <p>
     * 根据菜单id获取角色id列表
     * </p>
     *
     * @param menuId
     * @return java.util.Set<java.lang.Long>
     * @author daiqi
     * @date 2021/1/8 0:08
     */
    Set<Long> listRoleIdsByMenuId(Long menuId);
}
