package com.quasar.sika.design.server.business.user.service;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.sika.code.standard.base.service.BaseStandardService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:11
 */
public interface UserService extends BaseStandardService<UserDTO> {

    /**
     * 更用菜单id获取用户列表数据
     *
     * @param menuId : 菜单id
     * @return
     */
    List<UserDTO> listUserByRoleId(Long menuId);

    /**
     * 根据username获取用户数据
     * @param username : 用户名
     * @return
     */
    UserDTO findByUsername(String username);

    /**
     * 根据email获取用户数据
     * @param email : 邮箱
     * @return
     */
    UserDTO findByEmail(String email);
}
