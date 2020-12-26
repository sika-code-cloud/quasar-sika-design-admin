package com.quasar.sika.design.server.business.rolemenu.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quasar.sika.design.server.business.common.util.BusinessUtil;
import com.quasar.sika.design.server.business.rolemenu.convert.RoleMenuConvert;
import com.quasar.sika.design.server.business.rolemenu.entity.RoleMenuEntity;
import com.quasar.sika.design.server.business.rolemenu.mapper.RoleMenuMapper;
import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;
import com.quasar.sika.design.server.business.rolemenu.pojo.query.RoleMenuQuery;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:46
 */
@Service(value = "roleMenuService")
public class RoleMenuServiceImpl extends BaseStandardServiceImpl<RoleMenuMapper, RoleMenuEntity, RoleMenuDTO> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenuDTO> listByRoleId(Long roleId) {
        if (BaseUtil.isNull(roleId)) {
            return Lists.newArrayList();
        }
        Set<Long> roleIds = Sets.newLinkedHashSet();
        roleIds.add(roleId);
        return listByRoleIds(roleIds);
    }

    @Override
    public List<RoleMenuDTO> listByRoleIds(Set<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        RoleMenuQuery query = new RoleMenuQuery();
        query.setRoleIds(roleIds);
        return list(query);
    }

    @Override
    public List<RoleMenuDTO> listByMenuId(Long menuId) {
        if (BaseUtil.isNull(menuId)) {
            return Lists.newArrayList();
        }
        Set<Long> menuIds = Sets.newLinkedHashSet();
        menuIds.add(menuId);
        return listByMenuIds(menuIds);
    }

    @Override
    public List<RoleMenuDTO> listByMenuIds(Set<Long> menuIds) {
        if (CollUtil.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }
        RoleMenuQuery query = new RoleMenuQuery();
        query.setMenuIds(menuIds);
        return list(query);
    }

    @Override
    public Set<Long> listRoleIdsByMenuId(Long menuId) {
        List<RoleMenuDTO> roleMenuDTOS = listByMenuId(menuId);
        return BusinessUtil.mapToId(roleMenuDTOS, RoleMenuDTO.class);
    }

    @Override
    public Set<Long> listRoleIdsByMenuIds(Set<Long> menuIds) {
        List<RoleMenuDTO> roleMenuDTOS = listByMenuIds(menuIds);
        return BusinessUtil.mapToId(roleMenuDTOS, RoleMenuDTO.class);
    }

    @Override
    public Set<Long> listMenuIdsByRoleId(Long roleId) {
        List<RoleMenuDTO> roleMenuDTOS = listByRoleId(roleId);
        return BusinessUtil.mapToId(roleMenuDTOS, RoleMenuDTO.class);
    }

    @Override
    public Set<Long> listMenuIdsByRoleIds(Set<Long> roleIds) {
        List<RoleMenuDTO> roleMenuDTOS = listByRoleIds(roleIds);
        return BusinessUtil.mapToId(roleMenuDTOS, RoleMenuDTO.class);
    }

    @Override
    protected BaseConvert<RoleMenuEntity, RoleMenuDTO> convert() {
        return RoleMenuConvert.INSTANCE;
    }
}

