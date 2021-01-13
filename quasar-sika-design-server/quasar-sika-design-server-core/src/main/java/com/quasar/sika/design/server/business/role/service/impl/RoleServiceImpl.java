package com.quasar.sika.design.server.business.role.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.role.convert.RoleConvert;
import com.quasar.sika.design.server.business.role.entity.RoleEntity;
import com.quasar.sika.design.server.business.role.mapper.RoleMapper;
import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.quasar.sika.design.server.business.role.pojo.query.RoleQuery;
import com.quasar.sika.design.server.business.role.service.RoleService;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.quasar.sika.design.server.business.userrole.service.UserRoleService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:57
 */
@Service(value = "roleService")
public class RoleServiceImpl extends BaseStandardServiceImpl<RoleMapper, RoleEntity, RoleDTO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<RoleDTO> listByUserId(Long userId) {
        Set<Long> roleIds = userRoleService.listRoleIdsByUserId(userId);
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        RoleQuery query = new RoleQuery();
        query.setIds(roleIds);
        return list(query);
    }

    @Override
    public List<RoleDTO> listByMenuId(Long menuId) {
        Set<Long> roleIds = roleMenuService.listRoleIdsByMenuId(menuId);
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        RoleQuery query = new RoleQuery();
        query.setIds(roleIds);
        return list(query);
    }

    @Override
    protected BaseConvert<RoleEntity, RoleDTO> convert() {
        return RoleConvert.INSTANCE;
    }
}

