package com.quasar.sika.design.server.business.menu.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.quasar.sika.design.server.business.menu.convert.MenuConvert;
import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import com.quasar.sika.design.server.business.menu.mapper.MenuMapper;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:10
 */
@Service(value = "menuService")
public class MenuServiceImpl extends BaseStandardServiceImpl<MenuMapper, MenuEntity, MenuDTO> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<MenuDTO> listByRoleId(Long roleId) {
        Set<Long> menuIds = roleMenuService.listMenuIdsByRoleId(roleId);
        if (CollUtil.isEmpty(menuIds)) {
            return Lists.newArrayList();
        }
        MenuQuery query = new MenuQuery();
        query.setIds(menuIds);
        return list(query);
    }

    @Override
    protected BaseConvert<MenuEntity, MenuDTO> convert() {
        return MenuConvert.INSTANCE;
    }
}

