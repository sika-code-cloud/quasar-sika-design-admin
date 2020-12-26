package com.quasar.sika.design.server.business.role.service;

import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:19
 */
public interface RoleService extends BaseStandardService<RoleDTO> {
    List<RoleDTO> listRoleByMenuId(Long menuId);
}
