package com.quasar.sika.design.server.business.testorder.controller;


import com.quasar.sika.design.server.business.testorder.pojo.dto.TestOrderDTO;
import com.quasar.sika.design.server.business.testorder.pojo.query.TestOrderQuery;
import com.quasar.sika.design.server.business.testorder.service.TestOrderService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 测试订单表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:32
 */
@RestController(value = "testOrderController")
@RequestMapping("test_order")
public class TestOrderController extends BaseStandardController {
    @Autowired
    private TestOrderService testOrderService;

    @RequestMapping(value = "save/anon")
    public Result save(@RequestBody TestOrderDTO testOrderDto) {
        return super.success(testOrderService.save(testOrderDto));
    }

    @RequestMapping(value = "save_batch/anon")
    public Result saveBatch(@RequestBody List<TestOrderDTO> testOrderDtos) {
        return super.success(testOrderService.saveForBatch(testOrderDtos));
    }

    @RequestMapping(value = "update_by_id/anon")
    public Result updateById(@RequestBody TestOrderDTO testOrderDto) {
        return super.success(testOrderService.updateById(testOrderDto));
    }

    @RequestMapping(value = "page/anon")
    public Result page(@RequestBody TestOrderQuery testOrderQuery) {
        return super.success(testOrderService.page(testOrderQuery));
    }

    @RequestMapping(value = "find/anon")
    public Result find(@RequestBody TestOrderQuery testOrderQuery) {
        return super.success(testOrderService.find(testOrderQuery));
    }

    @RequestMapping(value = "list/anon")
    public Result list(@RequestBody TestOrderQuery testOrderQuery) {
        return super.success(testOrderService.list(testOrderQuery));
    }
}
