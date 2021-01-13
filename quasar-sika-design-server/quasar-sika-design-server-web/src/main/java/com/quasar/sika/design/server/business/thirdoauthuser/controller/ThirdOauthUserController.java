package com.quasar.sika.design.server.business.thirdoauthuser.controller;


import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.business.thirdoauthuser.pojo.query.ThirdOauthUserQuery;
import com.quasar.sika.design.server.business.thirdoauthuser.service.ThirdOauthUserService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 第三方授权用户表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:35
 */
@RestController(value = "thirdOauthUserController")
@RequestMapping("third_oauth_user")
public class ThirdOauthUserController extends BaseStandardController {
    @Autowired
    private ThirdOauthUserService thirdOauthUserService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ThirdOauthUserDTO thirdOauthUserDto) {
        return super.success(thirdOauthUserService.save(thirdOauthUserDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<ThirdOauthUserDTO> thirdOauthUserDtos) {
        return super.success(thirdOauthUserService.saveForBatch(thirdOauthUserDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody ThirdOauthUserDTO thirdOauthUserDto) {
        return super.success(thirdOauthUserService.updateById(thirdOauthUserDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ThirdOauthUserQuery thirdOauthUserQuery) {
        return super.success(thirdOauthUserService.page(thirdOauthUserQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ThirdOauthUserQuery thirdOauthUserQuery) {
        return super.success(thirdOauthUserService.find(thirdOauthUserQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ThirdOauthUserQuery thirdOauthUserQuery) {
        return super.success(thirdOauthUserService.list(thirdOauthUserQuery));
    }
}
