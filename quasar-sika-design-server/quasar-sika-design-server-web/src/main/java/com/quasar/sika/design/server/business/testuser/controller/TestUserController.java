package com.quasar.sika.design.server.business.testuser.controller;


import com.quasar.sika.design.server.business.testuser.pojo.dto.TestUserDTO;
import com.quasar.sika.design.server.business.testuser.pojo.query.TestUserQuery;
import com.quasar.sika.design.server.business.testuser.service.TestUserService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 测试用户表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:06
 */
@RestController(value = "testUserController")
@RequestMapping("test_user")
public class TestUserController extends BaseStandardController {
    @Autowired
    private TestUserService testUserService;

    @RequestMapping(value = "save/anon")
    public Result save(@RequestBody TestUserDTO testUserDto) {
        return super.success(testUserService.save(testUserDto));
    }

    @RequestMapping(value = "save_batch/anon")
    public Result saveBatch(@RequestBody List<TestUserDTO> testUserDtos) {
        return super.success(testUserService.saveForBatch(testUserDtos));
    }

    @RequestMapping(value = "update_by_id/anon")
    public Result updateById(@RequestBody TestUserDTO testUserDto) {
        return super.success(testUserService.updateById(testUserDto));
    }

    @RequestMapping(value = "page/anon")
    public Result page(@RequestBody TestUserQuery testUserQuery) {
        return super.success(testUserService.page(testUserQuery));
    }

    @RequestMapping(value = "find/anon")
    public Result find(@RequestBody TestUserQuery testUserQuery) {
        return super.success(testUserService.find(testUserQuery));
    }

    @RequestMapping(value = "list/anon")
    public Result list(@RequestBody TestUserQuery testUserQuery) {
        return super.success(testUserService.list(testUserQuery));
    }
}
