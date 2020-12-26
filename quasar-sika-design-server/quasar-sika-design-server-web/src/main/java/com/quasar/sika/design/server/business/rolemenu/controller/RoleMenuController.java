package com.quasar.sika.design.server.business.rolemenu.controller;


import com.quasar.sika.design.server.business.rolemenu.pojo.dto.RoleMenuDTO;
import com.quasar.sika.design.server.business.rolemenu.pojo.query.RoleMenuQuery;
import com.quasar.sika.design.server.business.rolemenu.service.RoleMenuService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:47
 */
@RestController(value = "roleMenuController")
@RequestMapping("role_menu")
public class RoleMenuController extends BaseStandardController {
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody RoleMenuDTO roleMenuDto) {
        return super.generateResult(roleMenuService.save(roleMenuDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<RoleMenuDTO> roleMenuDtos) {
        return super.generateResult(roleMenuService.saveForBatch(roleMenuDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody RoleMenuDTO roleMenuDto) {
        return super.generateResult(roleMenuService.updateById(roleMenuDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody RoleMenuQuery roleMenuQuery) {
        return super.generateResult(roleMenuService.page(roleMenuQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody RoleMenuQuery roleMenuQuery) {
        return super.generateResult(roleMenuService.find(roleMenuQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody RoleMenuQuery roleMenuQuery) {
        return super.generateResult(roleMenuService.list(roleMenuQuery));
    }
}
