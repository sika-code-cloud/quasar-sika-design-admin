package com.quasar.sika.design.server.common.auth.context;

import com.quasar.sika.design.server.common.auth.executor.AuthFindBackPasswordExecutor;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthForgetPasswordRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.context.CheckMailCodeContext;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 找回密码请求逻辑类
 * @date 2021/1/17 22:02
 */
@Data
@Accessors(chain = true)
public class AuthFindBackPasswordContext extends BaseStandardContext {
    private AuthForgetPasswordRequest findBackPasswordRequest;
    private CheckMailRequest checkMailRequest;
    protected CaptchaCheckRequest captchaCheckRequest;
    private CheckMailCodeContext checkMailCodeContext;

    @Override
    protected Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return AuthFindBackPasswordExecutor.class;
    }

    @Override
    public void initCustomer() {
        this.checkMailCodeContext = new CheckMailCodeContext().setRequest(checkMailRequest);
    }

}
