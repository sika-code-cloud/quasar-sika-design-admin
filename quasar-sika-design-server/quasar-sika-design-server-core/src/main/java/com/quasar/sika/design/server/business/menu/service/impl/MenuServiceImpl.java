package com.quasar.sika.design.server.business.menu.service.impl;

import com.quasar.sika.design.server.business.menu.entity.MenuEntity;
import com.quasar.sika.design.server.business.menu.mapper.MenuMapper;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.menu.convert.MenuConvert;

import com.quasar.sika.design.server.business.rolemenu.mapper.RoleMenuMapper;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:29
 */
@Service(value = "menuService")
public class MenuServiceImpl extends BaseStandardServiceImpl<MenuMapper, MenuEntity, MenuDTO> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuDTO> listMenuByRoleId(Long roleId) {

        return convert().convertToDTOs(menuMapper.listMenuByRoleId(roleId));
    }

    @Override
    protected BaseConvert<MenuEntity, MenuDTO> convert() {
        return MenuConvert.INSTANCE;
    }
}

