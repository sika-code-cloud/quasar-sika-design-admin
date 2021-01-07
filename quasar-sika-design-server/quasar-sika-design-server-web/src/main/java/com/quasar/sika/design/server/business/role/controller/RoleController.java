package com.quasar.sika.design.server.business.role.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.role.service.RoleService;
import com.quasar.sika.design.server.business.role.pojo.dto.RoleDTO;
import com.quasar.sika.design.server.business.role.pojo.query.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:59
 */
@RestController(value = "roleController")
@RequestMapping("role")
public class RoleController extends BaseStandardController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody RoleDTO roleDto) {
        return super.success(roleService.save(roleDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<RoleDTO> roleDtos) {
        return super.success(roleService.saveForBatch(roleDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody RoleDTO roleDto) {
        return super.success(roleService.updateById(roleDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.page(roleQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.find(roleQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody RoleQuery roleQuery) {
        return super.success(roleService.list(roleQuery));
    }
}
