package com.quasar.sika.design.server.business.userrole.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quasar.sika.design.server.business.common.util.BusinessUtil;
import com.quasar.sika.design.server.business.userrole.convert.UserRoleConvert;
import com.quasar.sika.design.server.business.userrole.entity.UserRoleEntity;
import com.quasar.sika.design.server.business.userrole.mapper.UserRoleMapper;
import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;
import com.quasar.sika.design.server.business.userrole.pojo.query.UserRoleQuery;
import com.quasar.sika.design.server.business.userrole.service.UserRoleService;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:37
 */
@Service(value = "userRoleService")
public class UserRoleServiceImpl extends BaseStandardServiceImpl<UserRoleMapper, UserRoleEntity, UserRoleDTO> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleDTO> listByRoleId(Long roleId) {
        if (BaseUtil.isNull(roleId)) {
            return Lists.newArrayList();
        }
        Set<Long> roleIds = Sets.newLinkedHashSet();
        roleIds.add(roleId);
        return listByRoleIds(roleIds);
    }

    @Override
    public List<UserRoleDTO> listByRoleIds(Set<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        UserRoleQuery query = new UserRoleQuery();
        query.setRoleIds(roleIds);
        return list(query);
    }

    @Override
    public List<UserRoleDTO> listByUserId(Long userId) {
        if (BaseUtil.isNull(userId)) {
            return Lists.newArrayList();
        }
        Set<Long> userIds = Sets.newLinkedHashSet();
        userIds.add(userId);
        return listByUserIds(userIds);
    }

    @Override
    public List<UserRoleDTO> listByUserIds(Set<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        UserRoleQuery query = new UserRoleQuery();
        query.setUserIds(userIds);
        return list(query);
    }

    @Override
    public Set<Long> listRoleIdsByUserId(Long userId) {
        List<UserRoleDTO> UserRoleDTOS = listByUserId(userId);
        return BusinessUtil.mapToId(UserRoleDTOS, UserRoleDTO.class);
    }

    @Override
    public Set<Long> listRoleIdsByUserIds(Set<Long> userIds) {
        List<UserRoleDTO> UserRoleDTOS = listByUserIds(userIds);
        return BusinessUtil.mapToId(UserRoleDTOS, UserRoleDTO.class);
    }

    @Override
    public Set<Long> listUserIdsByRoleId(Long roleId) {
        List<UserRoleDTO> UserRoleDTOS = listByRoleId(roleId);
        return BusinessUtil.mapToId(UserRoleDTOS, UserRoleDTO.class);
    }

    @Override
    public Set<Long> listUserIdsByRoleIds(Set<Long> roleIds) {
        List<UserRoleDTO> UserRoleDTOS = listByRoleIds(roleIds);
        return BusinessUtil.mapToId(UserRoleDTOS, UserRoleDTO.class);
    }

    @Override
    protected BaseConvert<UserRoleEntity, UserRoleDTO> convert() {
        return UserRoleConvert.INSTANCE;
    }
}

