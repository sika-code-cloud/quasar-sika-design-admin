package com.quasar.sika.design.server.business.user.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.business.user.pojo.query.UserQuery;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    @Value("${headImagePath}")
    private String headImagePath;

    @PostMapping("/upload_head")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "name", required = false) String name) {
        if (file.isEmpty()) {
            return fail("上传失败，请选择文件");
        }
        log.info("name" + name);
        String fileName = UUID.randomUUID().toString() + "." + FileUtil.getSuffix(file.getOriginalFilename());
        String filePath = headImagePath;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return success("imgs/head/" + fileName);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return fail("上传失败");
    }

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
