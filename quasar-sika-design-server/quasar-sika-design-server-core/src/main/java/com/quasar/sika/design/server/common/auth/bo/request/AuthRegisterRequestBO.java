package com.quasar.sika.design.server.common.auth.bo.request;

import cn.hutool.core.util.BooleanUtil;
import com.quasar.sika.design.server.common.auth.bo.response.AuRegisterResponseBO;
import com.quasar.sika.design.server.common.auth.domain.AuthDomain;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.bo.request.checker.CheckMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.bo.request.checker.CheckUserRegisterMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.executor.DomainExecutor;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuthRegisterRequestBO extends BaseStandardRequestBO<AuRegisterResponseBO> implements AuthDomain {
    private AuthRegisterRequest registerRequest;
    private CheckMailRequest checkMailRequest;
    protected CaptchaCheckRequest captchaCheckRequest;

    private CheckMailCodeRequestBO checkMailCodeRequestBO;
    private String clientPassword;
    @Override
    protected void init() {
        checkMailCodeRequestBO = new CheckUserRegisterMailCodeRequestBO().setRequest(checkMailRequest);
        // 初始化密码
        clientPassword = registerRequest.getPassword();
        registerRequest.setPassword(SHA256Util.sha256(registerRequest));
    }

    @Override
    protected void verify() {
        // 图片验证码校验
        captchaService().checkCaptchaVerifyCode(captchaCheckRequest);
        // 邮箱验证码校验
        DomainExecutor.execute(checkMailCodeRequestBO);
        // 校验用户名
        authService().checkRegisterUsername(registerRequest);
        // 校验邮箱
        authService().checkRegisterEmail(registerRequest);
        // 校验手机号
        authService().checkRegisterPhone(registerRequest);
    }

    @Override
    protected AuRegisterResponseBO doExecute() {
        boolean saveSuccess = userService().save(registerRequest);
        if (BooleanUtil.isFalse(saveSuccess)) {
            throw new BusinessException("保存失败,请校验注册参数");
        }
        // 执行完成后
        executeAfter();
        return newResponseBO(AuthResponse.success(registerRequest));
    }

    private void executeAfter() {
        // 自动登录
        AuthLoginRequest request = new AuthLoginRequest()
                .setUsername(registerRequest.getUsername())
                .setPassword(clientPassword);
        authService().login(request);
        // 移除缓存
        captchaService().removeCaptchaVerifyCode(captchaCheckRequest);
        checkMailCodeRequestBO.removeCode();
    }

    @Override
    public Class<AuRegisterResponseBO> responseClass() {
        return AuRegisterResponseBO.class;
    }
}
