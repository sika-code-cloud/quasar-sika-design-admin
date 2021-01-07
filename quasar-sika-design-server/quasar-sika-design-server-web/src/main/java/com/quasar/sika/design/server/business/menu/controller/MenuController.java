package com.quasar.sika.design.server.business.menu.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.menu.service.MenuService;
import com.quasar.sika.design.server.business.menu.pojo.dto.MenuDTO;
import com.quasar.sika.design.server.business.menu.pojo.query.MenuQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:13
 */
@RestController(value = "menuController")
@RequestMapping("menu")
public class MenuController extends BaseStandardController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody MenuDTO menuDto) {
        return super.success(menuService.save(menuDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<MenuDTO> menuDtos) {
        return super.success(menuService.saveForBatch(menuDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody MenuDTO menuDto) {
        return super.success(menuService.updateById(menuDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.page(menuQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.find(menuQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody MenuQuery menuQuery) {
        return super.success(menuService.list(menuQuery));
    }
}
