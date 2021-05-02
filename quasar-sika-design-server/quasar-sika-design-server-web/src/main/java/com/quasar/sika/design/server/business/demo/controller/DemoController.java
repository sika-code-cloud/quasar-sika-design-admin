package com.quasar.sika.design.server.business.demo.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.demo.service.DemoService;
import com.quasar.sika.design.server.business.demo.pojo.dto.DemoDTO;
import com.quasar.sika.design.server.business.demo.pojo.query.DemoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 示例表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 23:55:45
 */
@RestController(value = "demoController")
@RequestMapping("demo")
public class DemoController extends BaseStandardController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody DemoDTO demoDto) {
        return super.success(demoService.save(demoDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<DemoDTO> demoDtos) {
        return super.success(demoService.saveForBatch(demoDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody DemoDTO demoDto) {
        return super.success(demoService.updateById(demoDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody DemoQuery demoQuery) {
        return super.success(demoService.page(demoQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody DemoQuery demoQuery) {
        return super.success(demoService.find(demoQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody DemoQuery demoQuery) {
        return super.success(demoService.list(demoQuery));
    }
}
