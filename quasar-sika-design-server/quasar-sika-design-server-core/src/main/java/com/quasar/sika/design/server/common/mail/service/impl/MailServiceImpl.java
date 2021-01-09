package com.quasar.sika.design.server.common.mail.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.alibaba.fastjson.JSONObject;
import com.quasar.sika.design.server.business.mailtemplate.pojo.dto.MailTemplateDTO;
import com.quasar.sika.design.server.business.mailtemplate.service.MailTemplateService;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.quasar.sika.design.server.common.mail.service.MailService;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sikadai
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private MailTemplateService mailTemplateService;

    @Override
    public SendMailResponse sendMail(SendMailRequest request) {
        // 参数校验
        verify(request);
        // 构建邮件内容
        String result = buildMailContent(request);
        // 发送并返回messageId
        return new SendMailResponse().setMessageId(doSendMail(request, result));
    }

    private void verify(SendMailRequest request) {
        // 基础校验
        Assert.verifyObjNull(request, "发送邮件验证码请求对象");
        Assert.verifyStrEmpty(request.getContent(), "邮件内容");
        Assert.verifyStrEmpty(request.getSubject(), "邮件主题");
        Assert.verifyObjNull(request.getType(), "邮件验证码type");
        Validator.validateEmail(request.getTo(), "邮箱格式有误");
        // 类型校验
        MailCodeEnum codeTypeEnum = TypeEnumInf.find(request.getType(), MailCodeEnum.class);
        Assert.verifyObjNull(codeTypeEnum, StrUtil.format("邮件验证码code【{}】对应的邮件验证码枚举", request.getType()));
    }

    private String buildMailContent(SendMailRequest request) {
        // 查询模板
        MailTemplateDTO mailTemplate = mailTemplateService.findByType(request.getType());
        Assert.verifyDataNotExistent(mailTemplate, StrUtil.format("邮件模板type【{}】对应的数据", request.getType()));
        Assert.verifyStrEmpty(mailTemplate.getContent(), StrUtil.format("邮件模板type【{}】对应的内容", request.getType()));
        // 使用模板引擎组件邮件内容
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        Template template = engine.getTemplate(mailTemplate.getContent());
        log.info("发送邮件的参数为【{}】", JSONObject.toJSONString(request));
        return template.render(Dict.create().set("model", request));
    }

    private String doSendMail(SendMailRequest request, String result) {
        // 发送并返回messageId
        MailAccount mailAccount = new MailAccount().setFrom("sikacode@163.com").setPass("BFGUTRYNZDCTEQUY");
        return MailUtil.send(mailAccount, request.getTo(), request.getSubject(), result, request.isHtml());
    }

}
