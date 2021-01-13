package com.quasar.sika.design.server.business.user.controller;


import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.pojo.query.UserQuery;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:13
 */
@RestController(value = "userController")
@RequestMapping("user")
public class UserController extends BaseStandardController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserDTO userDto) {
        return super.success(userService.save(userDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<UserDTO> userDtos) {
        return super.success(userService.saveForBatch(userDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody UserDTO userDto) {
        return super.success(userService.updateById(userDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody UserQuery userQuery) {
        return super.success(userService.page(userQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody UserQuery userQuery) {
        return super.success(userService.find(userQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody UserQuery userQuery) {
        return super.success(userService.list(userQuery));
    }
}
