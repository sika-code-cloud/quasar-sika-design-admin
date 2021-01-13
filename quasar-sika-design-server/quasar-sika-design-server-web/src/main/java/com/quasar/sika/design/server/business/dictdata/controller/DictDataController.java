package com.quasar.sika.design.server.business.dictdata.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.dictdata.service.DictDataService;
import com.quasar.sika.design.server.business.dictdata.pojo.dto.DictDataDTO;
import com.quasar.sika.design.server.business.dictdata.pojo.query.DictDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:37
 */
@RestController(value = "dictDataController")
@RequestMapping("dict_data")
public class DictDataController extends BaseStandardController {
    @Autowired
    private DictDataService dictDataService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody DictDataDTO dictDataDto) {
        return super.success(dictDataService.save(dictDataDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<DictDataDTO> dictDataDtos) {
        return super.success(dictDataService.saveForBatch(dictDataDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody DictDataDTO dictDataDto) {
        return super.success(dictDataService.updateById(dictDataDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody DictDataQuery dictDataQuery) {
        return super.success(dictDataService.page(dictDataQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody DictDataQuery dictDataQuery) {
        return super.success(dictDataService.find(dictDataQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody DictDataQuery dictDataQuery) {
        return super.success(dictDataService.list(dictDataQuery));
    }
}
