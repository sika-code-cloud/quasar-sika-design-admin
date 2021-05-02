package com.quasar.sika.design.server.business.userrole.service.impl;

import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.userrole.entity.UserRoleEntity;
import com.quasar.sika.design.server.business.userrole.mapper.UserRoleMapper;
import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;
import com.quasar.sika.design.server.business.userrole.pojo.query.UserRoleQuery;
import com.quasar.sika.design.server.business.userrole.service.UserRoleService;
import com.quasar.sika.design.server.business.userrole.convert.UserRoleConvert;

import com.quasar.sika.design.server.common.normal.util.CommonUtil;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:53
 */
@Service(value = "userRoleService")
public class UserRoleServiceImpl extends BaseStandardServiceImpl<UserRoleMapper, UserRoleEntity, UserRoleDTO> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<Long> listUserIdsByRoleId(Long roleId) {
        List<UserRoleDTO> userRoles = listByRoleId(roleId);
        return CommonUtil.mapToField(userRoles, "userId");
    }

    @Override
    public Set<Long> listRoleIdsByUserId(Long userId) {
        List<UserRoleDTO> userRoles = listByUserId(userId);
        return CommonUtil.mapToField(userRoles, "roleId");
    }

    public List<UserRoleDTO> listByUserId(Long userId) {
        if (userId == null) {
            return Lists.newArrayList();
        }
        UserRoleQuery query = new UserRoleQuery();
        query.setUserId(userId);
        return list(query);
    }

    public List<UserRoleDTO> listByRoleId(Long roleId) {
        if (roleId == null) {
            return Lists.newArrayList();
        }
        UserRoleQuery query = new UserRoleQuery();
        query.setRoleId(roleId);
        return list(query);
    }

    @Override
    protected BaseConvert<UserRoleEntity, UserRoleDTO> convert() {
        return UserRoleConvert.INSTANCE;
    }
}

