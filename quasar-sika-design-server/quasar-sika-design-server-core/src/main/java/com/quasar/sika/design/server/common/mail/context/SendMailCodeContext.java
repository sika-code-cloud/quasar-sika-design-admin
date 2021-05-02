package com.quasar.sika.design.server.common.mail.context;

import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.executor.SendMailCodeExecutor;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 认证登录上下文
 * @date 2021/4/423:20
 */
@Data
@Accessors(chain = true)
public class SendMailCodeContext extends BaseStandardContext {
    public static final Integer RANDOM_LENGTH = 6;
    protected CaptchaCheckRequest captchaCheckRequest;
    protected SendMailRequest request;

    @Override
    public Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return SendMailCodeExecutor.class;
    }

}
