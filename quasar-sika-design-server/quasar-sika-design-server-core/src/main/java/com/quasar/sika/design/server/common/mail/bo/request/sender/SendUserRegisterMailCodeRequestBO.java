package com.quasar.sika.design.server.common.mail.bo.request.sender;

import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.SendMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.sika.code.basic.util.Assert;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送用户注册邮件验证码
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class SendUserRegisterMailCodeRequestBO extends SendMailCodeRequestBO {

    protected CaptchaCheckRequest captchaCheckRequest;

    @Override
    protected void init() {
        super.buildRequest(MailCodeEnum.USER_REGISTER);
    }

    @Override
    protected void verify() {
        super.verify();
        Assert.verifyObjNull(captchaCheckRequest, "图片请求对象");
        Assert.verifyStrEmpty(captchaCheckRequest.getClientCode(), "图片验证码");
        Assert.verifyObjNull(captchaCheckRequest.getType(), "图片验证码类型");
    }

    @Override
    protected SendMailCodeResponseBO doExecute() {
        // 1： 校验图片验证码
        captchaService().checkCaptchaVerifyCode(captchaCheckRequest);
        return super.doExecute();
    }
}
