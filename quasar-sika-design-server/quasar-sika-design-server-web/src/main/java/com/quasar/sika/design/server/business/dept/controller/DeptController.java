package com.quasar.sika.design.server.business.dept.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.dept.service.DeptService;
import com.quasar.sika.design.server.business.dept.pojo.dto.DeptDTO;
import com.quasar.sika.design.server.business.dept.pojo.query.DeptQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:26
 */
@RestController(value = "deptController")
@RequestMapping("dept")
public class DeptController extends BaseStandardController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody DeptDTO deptDto) {
        return super.success(deptService.save(deptDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<DeptDTO> deptDtos) {
        return super.success(deptService.saveForBatch(deptDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody DeptDTO deptDto) {
        return super.success(deptService.updateById(deptDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody DeptQuery deptQuery) {
        return super.success(deptService.page(deptQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody DeptQuery deptQuery) {
        return super.success(deptService.find(deptQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody DeptQuery deptQuery) {
        return super.success(deptService.list(deptQuery));
    }
}
