package com.quasar.sika.design.server.business.notice.controller;


import java.util.List;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.quasar.sika.design.server.business.notice.service.NoticeService;
import com.quasar.sika.design.server.business.notice.pojo.dto.NoticeDTO;
import com.quasar.sika.design.server.business.notice.pojo.query.NoticeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:25
 */
@RestController(value = "noticeController")
@RequestMapping("notice")
public class NoticeController extends BaseStandardController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody NoticeDTO noticeDto) {
        return super.success(noticeService.save(noticeDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<NoticeDTO> noticeDtos) {
        return super.success(noticeService.saveForBatch(noticeDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody NoticeDTO noticeDto) {
        return super.success(noticeService.updateById(noticeDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody NoticeQuery noticeQuery) {
        return super.success(noticeService.page(noticeQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody NoticeQuery noticeQuery) {
        return super.success(noticeService.find(noticeQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody NoticeQuery noticeQuery) {
        return super.success(noticeService.list(noticeQuery));
    }
}
