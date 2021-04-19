package com.quasar.sika.design.server.common.auth.context;

import com.quasar.sika.design.server.common.auth.executor.AuthRegisterRequestExecutor;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.context.CheckMailCodeContext;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.sika.code.standard.base.pojo.context.BaseStandardContext;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuthRegisterContext extends BaseStandardContext {
    private AuthRegisterRequest registerRequest;
    private CheckMailRequest checkMailRequest;
    protected CaptchaCheckRequest captchaCheckRequest;
    private CheckMailCodeContext checkMailCodeContext;
    private Boolean bindOauthUser;

    @Override
    public void initCustomer() {
        checkMailCodeContext = new CheckMailCodeContext().setRequest(checkMailRequest);
        registerRequest.setPassword(SHA256Util.sha256(registerRequest));
    }

    @Override
    protected Class<? extends BaseStandardExecutor> buildExecutorClass() {
        return AuthRegisterRequestExecutor.class;
    }
}
