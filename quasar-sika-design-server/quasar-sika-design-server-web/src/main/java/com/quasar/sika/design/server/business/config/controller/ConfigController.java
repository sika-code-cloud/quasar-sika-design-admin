package com.quasar.sika.design.server.business.config.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.config.service.ConfigService;
import com.quasar.sika.design.server.business.config.pojo.dto.ConfigDTO;
import com.quasar.sika.design.server.business.config.pojo.query.ConfigQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:14
 */
@RestController(value = "configController")
@RequestMapping("config")
public class ConfigController extends BaseStandardController {
    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ConfigDTO configDto) {
        return super.success(configService.save(configDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<ConfigDTO> configDtos) {
        return super.success(configService.saveForBatch(configDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody ConfigDTO configDto) {
        return super.success(configService.updateById(configDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ConfigQuery configQuery) {
        return super.success(configService.page(configQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ConfigQuery configQuery) {
        return super.success(configService.find(configQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ConfigQuery configQuery) {
        return super.success(configService.list(configQuery));
    }
}
