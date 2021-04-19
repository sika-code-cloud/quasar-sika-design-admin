package com.quasar.sika.design.server.common.auth.executor;

import cn.hutool.core.util.BooleanUtil;
import com.quasar.sika.design.server.common.auth.context.AuthRegisterContext;
import com.quasar.sika.design.server.common.auth.domain.AuthDomain;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.executor.BaseStandardExecutor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuthRegisterRequestExecutor extends BaseStandardExecutor<AuthRegisterContext> implements AuthDomain {
    @Override
    protected void executeBefore() {
        verify();
    }

    protected void verify() {
        // 图片验证码校验
        captchaService().checkCaptchaVerifyCode(context.getCaptchaCheckRequest());
        // 邮箱验证码校验
        executorManager().execute(context.getCheckMailCodeContext());
        // 校验用户名
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        authService().checkRegisterUsername(registerRequest);
        // 校验邮箱
        authService().checkRegisterEmail(registerRequest);
        // 校验手机号
        authService().checkRegisterPhone(registerRequest);
    }

    @Override
    protected ServiceResult doExecute() {
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        boolean saveSuccess = userService().save(registerRequest);
        if (BooleanUtil.isFalse(saveSuccess)) {
            throw new BusinessException("保存失败,请校验注册参数");
        }
        return ServiceResult.newInstanceOfSucResult(AuthResponse.success(registerRequest));
    }

    @Override
    protected void executeAfter() {
        // 自动登录
        AuthRegisterRequest registerRequest = context.getRegisterRequest();
        AuthLoginRequest request = new AuthLoginRequest(registerRequest.getUsername(), registerRequest.getPassword());
        request.setEncryptedPassword(true);
        if (BooleanUtil.isTrue(context.getBindOauthUser())) {
            authService().bindOauthUser(request);
        } else {
            authService().login(request);
        }
        // 移除缓存
        captchaService().removeCaptchaVerifyCode(context.getCaptchaCheckRequest());
        mailService().removeMailCode(context.getCheckMailRequest());
    }
}
