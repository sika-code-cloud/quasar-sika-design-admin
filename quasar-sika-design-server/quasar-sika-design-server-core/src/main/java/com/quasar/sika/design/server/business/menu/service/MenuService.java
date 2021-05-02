package com.quasar.sika.design.server.business.menu.service;

import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:09
 */
public interface MenuService extends BaseStandardService<MenuDTO> {

    /**
     * <p>
     * 根据角色id查询菜单列表
     * </p>
     *
     * @param roleId
     * @return java.util.List<com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO>
     * @author daiqi
     * @date 2021/1/8 0:39
     */
    List<MenuDTO> listByRoleId(Long roleId);
}
