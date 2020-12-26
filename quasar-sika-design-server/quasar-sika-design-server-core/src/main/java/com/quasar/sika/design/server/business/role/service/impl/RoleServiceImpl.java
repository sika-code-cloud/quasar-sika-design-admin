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
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:20
 */
@Service(value = "roleService")
public class RoleServiceImpl extends BaseStandardServiceImpl<RoleMapper, RoleEntity, RoleDTO> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<RoleDTO> listRoleByMenuId(Long menuId) {
        Set<Long> roleIds = roleMenuService.listRoleIdsByMenuId(menuId);
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        RoleQuery roleQuery = new RoleQuery().setRoleIds(roleIds);
        return list(roleQuery);
    }

    @Override
    protected BaseConvert<RoleEntity, RoleDTO> convert() {
        return RoleConvert.INSTANCE;
    }
}

