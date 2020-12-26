package com.quasar.sika.design.server.business.role.controller;


import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.quasar.sika.design.server.business.role.pojo.query.RoleQuery;
import com.quasar.sika.design.server.business.role.service.RoleService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:22
 */
@RestController(value = "roleController")
@RequestMapping("role")
public class RoleController extends BaseStandardController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody RoleDTO roleDto) {
        return super.generateResult(roleService.save(roleDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<RoleDTO> roleDtos) {
        return super.generateResult(roleService.saveForBatch(roleDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody RoleDTO roleDto) {
        return super.generateResult(roleService.updateById(roleDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody RoleQuery roleQuery) {
        return super.generateResult(roleService.page(roleQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody RoleQuery roleQuery) {
        return super.generateResult(roleService.find(roleQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody RoleQuery roleQuery) {
        return super.generateResult(roleService.list(roleQuery));
    }
}
