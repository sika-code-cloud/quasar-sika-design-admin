package com.quasar.sika.design.server.business.rolemenu.service.impl;

import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.rolemenu.convert.RoleMenuConvert;
import com.quasar.sika.design.server.business.rolemenu.entity.RoleMenuEntity;
import com.quasar.sika.design.server.business.rolemenu.mapper.RoleMenuMapper;
import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;
import com.quasar.sika.design.server.business.rolemenu.pojo.query.RoleMenuQuery;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.quasar.sika.design.server.common.normal.util.CommonUtil;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:22
 */
@Service(value = "roleMenuService")
public class RoleMenuServiceImpl extends BaseStandardServiceImpl<RoleMenuMapper, RoleMenuEntity, RoleMenuDTO> implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Set<Long> listMenuIdsByRoleId(Long roleId) {
        List<RoleMenuDTO> roleMenus = listByRoleId(roleId);
        return CommonUtil.mapToField(roleMenus, "roleId");
    }

    public List<RoleMenuDTO> listByRoleId(Long roleId) {
        if (roleId == null) {
            return Lists.newArrayList();
        }
        RoleMenuQuery query = new RoleMenuQuery();
        query.setRoleId(roleId);
        return list(query);
    }

    @Override
    public Set<Long> listRoleIdsByMenuId(Long menuId) {
        List<RoleMenuDTO> roleMenus = listByRoleId(menuId);
        return CommonUtil.mapToField(roleMenus, "menuId");
    }

    public List<RoleMenuDTO> listByMenuId(Long menuId) {
        if (menuId == null) {
            return Lists.newArrayList();
        }
        RoleMenuQuery query = new RoleMenuQuery();
        query.setMenuId(menuId);
        return list(query);
    }

    @Override
    protected BaseConvert<RoleMenuEntity, RoleMenuDTO> convert() {
        return RoleMenuConvert.INSTANCE;
    }
}

