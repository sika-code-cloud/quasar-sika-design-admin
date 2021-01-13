package com.quasar.sika.design.server.business.dicttype.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.dicttype.service.DictTypeService;
import com.quasar.sika.design.server.business.dicttype.pojo.dto.DictTypeDTO;
import com.quasar.sika.design.server.business.dicttype.pojo.query.DictTypeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:49
 */
@RestController(value = "dictTypeController")
@RequestMapping("dict_type")
public class DictTypeController extends BaseStandardController {
    @Autowired
    private DictTypeService dictTypeService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody DictTypeDTO dictTypeDto) {
        return super.success(dictTypeService.save(dictTypeDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<DictTypeDTO> dictTypeDtos) {
        return super.success(dictTypeService.saveForBatch(dictTypeDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody DictTypeDTO dictTypeDto) {
        return super.success(dictTypeService.updateById(dictTypeDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody DictTypeQuery dictTypeQuery) {
        return super.success(dictTypeService.page(dictTypeQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody DictTypeQuery dictTypeQuery) {
        return super.success(dictTypeService.find(dictTypeQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody DictTypeQuery dictTypeQuery) {
        return super.success(dictTypeService.list(dictTypeQuery));
    }
}
