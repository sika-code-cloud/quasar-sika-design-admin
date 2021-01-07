package com.quasar.sika.design.server.business.operlog.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.operlog.service.OperLogService;
import com.quasar.sika.design.server.business.operlog.pojo.dto.OperLogDTO;
import com.quasar.sika.design.server.business.operlog.pojo.query.OperLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:36
 */
@RestController(value = "operLogController")
@RequestMapping("oper_log")
public class OperLogController extends BaseStandardController {
    @Autowired
    private OperLogService operLogService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody OperLogDTO operLogDto) {
        return super.success(operLogService.save(operLogDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<OperLogDTO> operLogDtos) {
        return super.success(operLogService.saveForBatch(operLogDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody OperLogDTO operLogDto) {
        return super.success(operLogService.updateById(operLogDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody OperLogQuery operLogQuery) {
        return super.success(operLogService.page(operLogQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody OperLogQuery operLogQuery) {
        return super.success(operLogService.find(operLogQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody OperLogQuery operLogQuery) {
        return super.success(operLogService.list(operLogQuery));
    }
}
