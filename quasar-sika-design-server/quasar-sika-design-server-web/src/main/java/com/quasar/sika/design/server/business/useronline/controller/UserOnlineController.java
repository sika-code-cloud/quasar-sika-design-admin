package com.quasar.sika.design.server.business.useronline.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.useronline.service.UserOnlineService;
import com.quasar.sika.design.server.business.useronline.pojo.dto.UserOnlineDTO;
import com.quasar.sika.design.server.business.useronline.pojo.query.UserOnlineQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 在线用户记录 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:36:33
 */
@RestController(value = "userOnlineController")
@RequestMapping("user_online")
public class UserOnlineController extends BaseStandardController {
    @Autowired
    private UserOnlineService userOnlineService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody UserOnlineDTO userOnlineDto) {
        return super.success(userOnlineService.save(userOnlineDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<UserOnlineDTO> userOnlineDtos) {
        return super.success(userOnlineService.saveForBatch(userOnlineDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody UserOnlineDTO userOnlineDto) {
        return super.success(userOnlineService.updateById(userOnlineDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody UserOnlineQuery userOnlineQuery) {
        return super.success(userOnlineService.page(userOnlineQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody UserOnlineQuery userOnlineQuery) {
        return super.success(userOnlineService.find(userOnlineQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody UserOnlineQuery userOnlineQuery) {
        return super.success(userOnlineService.list(userOnlineQuery));
    }
}
