package com.quasar.sika.design.server.business.userrole.controller;


import com.quasar.sika.design.server.business.userrole.pojo.dto.UserRoleDTO;
import com.quasar.sika.design.server.business.userrole.pojo.query.UserRoleQuery;
import com.quasar.sika.design.server.business.userrole.service.UserRoleService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:39
 */
@RestController(value = "userRoleController")
@RequestMapping("user_role")
public class UserRoleController extends BaseStandardController {
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserRoleDTO userRoleDto) {
        return super.generateResult(userRoleService.save(userRoleDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<UserRoleDTO> userRoleDtos) {
        return super.generateResult(userRoleService.saveForBatch(userRoleDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody UserRoleDTO userRoleDto) {
        return super.generateResult(userRoleService.updateById(userRoleDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody UserRoleQuery userRoleQuery) {
        return super.generateResult(userRoleService.page(userRoleQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody UserRoleQuery userRoleQuery) {
        return super.generateResult(userRoleService.find(userRoleQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody UserRoleQuery userRoleQuery) {
        return super.generateResult(userRoleService.list(userRoleQuery));
    }
}
