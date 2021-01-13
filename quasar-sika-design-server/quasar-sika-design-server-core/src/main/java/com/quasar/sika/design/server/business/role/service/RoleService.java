package com.quasar.sika.design.server.business.role.service;

import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:56
 */
public interface RoleService extends BaseStandardService<RoleDTO> {
    /**
     * <p>
     * 根据用户id查询角色列表
     * </p>
     *
     * @param userId
     * @return java.util.List<com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO>
     * @author daiqi
     * @date 2021/1/8 0:34
     */
    List<RoleDTO> listByUserId(Long userId);

    /**
     * <p>
     * 根据菜单id查询角色列表
     * </p>
     *
     * @param menuId
     * @return java.util.List<com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO>
     * @author daiqi
     * @date 2021/1/8 0:34
     */
    List<RoleDTO> listByMenuId(Long menuId);
}
