package com.quasar.sika.design.server.business.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.user.convert.UserConvert;
import com.quasar.sika.design.server.business.user.entity.UserEntity;
import com.quasar.sika.design.server.business.user.mapper.UserMapper;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.pojo.query.UserQuery;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.business.userrole.service.UserRoleService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:12
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseStandardServiceImpl<UserMapper, UserEntity, UserDTO> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<UserDTO> listUserByRoleId(Long roleId) {
        Set<Long> userIds = userRoleService.listUserIdsByRoleId(roleId);
        if (CollUtil.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        UserQuery userQuery = new UserQuery().setIds(userIds);
        return list(userQuery);
    }

    @Override
    public UserDTO findByEmail(String email) {
        if (StrUtil.isBlank(email)) {
            return null;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(email);
        return find(userQuery);
    }

    @Override
    public UserDTO findByPhone(String phone) {
        if (StrUtil.isBlank(phone)) {
            return null;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setPhone(phone);
        return find(userQuery);
    }


    @Override
    public UserDTO findByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            return null;
        }
        UserQuery userQuery = new UserQuery();
        userQuery.setUsername(username);
        return find(userQuery);
    }

    @Override
    protected BaseConvert<UserEntity, UserDTO> convert() {
        return UserConvert.INSTANCE;
    }
}

