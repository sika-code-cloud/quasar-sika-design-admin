package com.quasar.sika.design.server.common.shiro.system.mapper;

import com.quasar.sika.design.server.common.shiro.system.entity.Menu;
import com.quasar.sika.design.server.common.shiro.system.entity.RoleMenu;
import com.sika.code.database.common.base.BaseMapper;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> 系统管理 - 角色-菜单关联表  Mapper 接口 </p>
 *
 * @author : zhengqing
 * @date : 2019-08-20
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 列表分页
     *
     * @param page
     * @param filter
     * @return
     */
    List<RoleMenu> selectRoleMenus(Pagination page, @Param("filter") RoleMenu filter);

    /**
     * 列表
     *
     * @param filter
     * @return
     */
    List<RoleMenu> selectRoleMenus(@Param("filter") RoleMenu filter);

    /**
     * 根据角色Id删除用户与菜单相关联数据
     *
     * @param roleId:
     * @return: void
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);


    /**
     * 根据角色ID查询关联菜单
     *
     * @param roleId:
     * @return: java.util.List<com.zhengqing.modules.system.entity.Menu>
     */
    List<Menu> selectMenusByRoleId(@Param("roleId") Integer roleId);

}
