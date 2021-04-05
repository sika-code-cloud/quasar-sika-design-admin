package com.quasar.sika.design.server.common.mail.executor;

import cn.hutool.core.util.RandomUtil;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.context.SendMailCodeContext;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.basic.util.Assert;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class SendMailCodeExecutor extends BaseStandardExecutor<SendMailCodeContext> implements MailDomain {
    @Override
    protected void executeBefore() {
        buildRequest();
        verifyEmailData();
        verifyCaptchaCode();
        // 1： 校验图片验证码
        captchaService().checkCaptchaVerifyCode(context.getCaptchaCheckRequest());
    }

    @Override
    protected ServiceResult doExecute() {
        return ServiceResult.newInstanceOfSucResult(senMailCode());
    }

    @Override
    protected void executeAfter() {
        super.executeAfter();
    }

    protected void buildRequest() {
        SendMailRequest request = context.getRequest();
        MailCodeEnum mailCodeEnum = TypeEnumInf.findByCode(request.getCode(), MailCodeEnum.class);
        Assert.verifyObjNullMsg(mailCodeEnum, "邮箱类型有误");
        request.setSubject(mailCodeEnum.getDesc());
        request.setType(mailCodeEnum.getType());
        request.setCode(mailCodeEnum.getCode());
        request.setOperate(mailCodeEnum.getDesc());
        request.setContent(RandomUtil.randomNumbers(SendMailCodeContext.RANDOM_LENGTH));
    }

    private void verifyEmailData() {
        SendMailRequest request = context.getRequest();
        Assert.verifyObjNull(request, "请求对象");
        Assert.verifyStrEmpty(request.getSubject(), "邮件主题");
        Assert.verifyObjNull(request.getType(), "邮件类型");
        Assert.verifyStrEmpty(request.getCode(), "邮件编码");
        Assert.verifyStrEmpty(request.getTo(), "接收者邮箱");

    }

    private void verifyCaptchaCode() {
        CaptchaCheckRequest captchaCheckRequest = context.getCaptchaCheckRequest();
        Assert.verifyObjNull(captchaCheckRequest, "图片请求对象");
        Assert.verifyStrEmpty(captchaCheckRequest.getClientCode(), "图片验证码");
        Assert.verifyObjNull(captchaCheckRequest.getType(), "图片验证码类型");
    }


    protected SendMailResponse senMailCode() {
        SendMailRequest request = context.getRequest();
        // 发送验证码
        SendMailResponse response = mailService().sendMail(request);
        // 缓存请求
        mailService().setMailCode(request);
        return response;
    }

}
