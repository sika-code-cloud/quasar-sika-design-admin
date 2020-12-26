package com.quasar.sika.design.server.business.userrole.service;

import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:37
 */
public interface UserRoleService extends BaseStandardService<UserRoleDTO> {
    List<UserRoleDTO> listByRoleId(Long roleId);

    List<UserRoleDTO> listByRoleIds(Set<Long> roleIds);

    List<UserRoleDTO> listByUserId(Long userId);

    List<UserRoleDTO> listByUserIds(Set<Long> userIds);

    Set<Long> listRoleIdsByUserId(Long userId);

    Set<Long> listRoleIdsByUserIds(Set<Long> userIds);

    Set<Long> listUserIdsByRoleId(Long roleId);

    Set<Long> listUserIdsByRoleIds(Set<Long> roleIds);
}
