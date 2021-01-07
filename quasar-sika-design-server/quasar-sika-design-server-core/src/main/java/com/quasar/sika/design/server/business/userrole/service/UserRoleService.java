package com.quasar.sika.design.server.business.userrole.service;

import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.Set;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:52
 */
public interface UserRoleService extends BaseStandardService<UserRoleDTO> {
    /**
     * <p>
     * 根据角色id获取用户id列表
     * </p>
     *
     * @param roleId
     * @return java.util.Set<java.lang.Long>
     * @author daiqi
     * @date 2021/1/8 0:08
     */
    Set<Long> listUserIdsByRoleId(Long roleId);
    /**
     * <p>
     * 根据用户id获取角色id列表
     * </p>
     *
     * @param userId
     * @return java.util.Set<java.lang.Long>
     * @author daiqi
     * @date 2021/1/8 0:08
     */
    Set<Long> listRoleIdsByUserId(Long userId);
}
