package com.quasar.sika.design.server.business.logininfor.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.logininfor.service.LogininforService;
import com.quasar.sika.design.server.business.logininfor.pojo.dto.LogininforDTO;
import com.quasar.sika.design.server.business.logininfor.pojo.query.LogininforQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:00
 */
@RestController(value = "logininforController")
@RequestMapping("logininfor")
public class LogininforController extends BaseStandardController {
    @Autowired
    private LogininforService logininforService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody LogininforDTO logininforDto) {
        return super.success(logininforService.save(logininforDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<LogininforDTO> logininforDtos) {
        return super.success(logininforService.saveForBatch(logininforDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody LogininforDTO logininforDto) {
        return super.success(logininforService.updateById(logininforDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody LogininforQuery logininforQuery) {
        return super.success(logininforService.page(logininforQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody LogininforQuery logininforQuery) {
        return super.success(logininforService.find(logininforQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody LogininforQuery logininforQuery) {
        return super.success(logininforService.list(logininforQuery));
    }
}
