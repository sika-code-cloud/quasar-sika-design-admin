package com.quasar.sika.design.server.business.mailtemplate.controller;


import com.quasar.sika.design.server.business.mailtemplate.pojo.dto.MailTemplateDTO;
import com.quasar.sika.design.server.business.mailtemplate.pojo.query.MailTemplateQuery;
import com.quasar.sika.design.server.business.mailtemplate.service.MailTemplateService;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * <p>
 * 邮件模板表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:08:13
 */
@RestController(value = "mailTemplateController")
@RequestMapping("mail_template")
public class MailTemplateController extends BaseStandardController {
    @Autowired
    private MailTemplateService mailTemplateService;

    @RequestMapping(value = "save")
    public Result save(@RequestBody MailTemplateDTO mailTemplateDto) {
        return super.success(mailTemplateService.save(mailTemplateDto));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody List<MailTemplateDTO> mailTemplateDtos) {
        return super.success(mailTemplateService.saveForBatch(mailTemplateDtos));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody MailTemplateDTO mailTemplateDto) {
        return super.success(mailTemplateService.updateById(mailTemplateDto));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody MailTemplateQuery mailTemplateQuery) {
        return super.success(mailTemplateService.page(mailTemplateQuery));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody MailTemplateQuery mailTemplateQuery) {
        return super.success(mailTemplateService.find(mailTemplateQuery));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody MailTemplateQuery mailTemplateQuery) {
        return super.success(mailTemplateService.list(mailTemplateQuery));
    }
}
