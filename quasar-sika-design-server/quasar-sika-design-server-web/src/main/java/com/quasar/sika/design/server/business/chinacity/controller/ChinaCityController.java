package com.quasar.sika.design.server.business.chinacity.controller;


import com.quasar.sika.design.server.business.chinacity.pojo.dto.ChinaCityDTO;
import com.quasar.sika.design.server.business.chinacity.pojo.query.ChinaCityQuery;
import com.quasar.sika.design.server.business.chinacity.service.ChinaCityService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:43
 */
@RestController(value = "chinaCityController")
@RequestMapping("china_city")
public class ChinaCityController extends BaseStandardController {
    @Autowired
    private ChinaCityService chinaCityService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ChinaCityDTO chinaCityDto) {
        return super.success(chinaCityService.save(chinaCityDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<ChinaCityDTO> chinaCityDtos) {
        return super.success(chinaCityService.saveForBatch(chinaCityDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody ChinaCityDTO chinaCityDto) {
        return super.success(chinaCityService.updateById(chinaCityDto));
    }

    @RequestMapping(value = "page/anon")
    public Result page(@RequestBody ChinaCityQuery chinaCityQuery) {
        return super.success(chinaCityService.page(chinaCityQuery));
    }

    @RequestMapping(value = "find/anon")
    public Result find(@RequestBody ChinaCityQuery chinaCityQuery) {
        return super.success(chinaCityService.find(chinaCityQuery));
    }

    @RequestMapping(value = "list/anon")
    public Result list(@RequestBody ChinaCityQuery chinaCityQuery) {
        return super.success(chinaCityService.list(chinaCityQuery));
    }
}
