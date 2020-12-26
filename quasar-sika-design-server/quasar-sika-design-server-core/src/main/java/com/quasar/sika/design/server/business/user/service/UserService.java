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

    List<UserDTO> listUserByRoleId(Long menuId);

    UserDTO findByUsername(String username);
}
