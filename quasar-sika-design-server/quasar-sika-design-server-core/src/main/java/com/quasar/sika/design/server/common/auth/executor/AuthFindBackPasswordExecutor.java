package com.quasar.sika.design.server.common.auth.executor;

import cn.hutool.core.util.BooleanUtil;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.auth.context.AuthFindBackPasswordContext;
import com.quasar.sika.design.server.common.auth.domain.AuthDomain;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthForgetPasswordRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.sika.code.basic.pojo.dto.ServiceResult;
import com.sika.code.exception.BusinessException;
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
public class AuthFindBackPasswordExecutor extends BaseStandardExecutor<AuthFindBackPasswordContext> implements AuthDomain {

    @Override
    protected void executeBefore() {
        verify();
        initBizData();
    }

    protected void verify() {
        // 图片验证码校验
        captchaService().checkCaptchaVerifyCode(context.getCaptchaCheckRequest());
        // 校验邮箱
        authService().checkForgetPasswordEmail(context.getFindBackPasswordRequest());
        // 邮箱验证码校验
        executorManager().execute(context.getCheckMailCodeContext());
    }

    private void initBizData() {
        AuthForgetPasswordRequest findBackPasswordRequest = context.getFindBackPasswordRequest();
        UserDTO userFromDb = userService().findByEmail(findBackPasswordRequest.getEmail());
        findBackPasswordRequest.setPassword(SHA256Util.sha256(findBackPasswordRequest.getPassword(), userFromDb.getUsername()));
        findBackPasswordRequest.setId(userFromDb.getId());
    }

    @Override
    protected ServiceResult doExecute() {
        AuthForgetPasswordRequest findBackPasswordRequest = context.getFindBackPasswordRequest();
        // 更新密码
        UserDTO userForUpdate = new UserDTO();
        userForUpdate.setId(findBackPasswordRequest.getId());
        userForUpdate.setPassword(findBackPasswordRequest.getPassword());
        boolean updateSuccess = userService().updateById(findBackPasswordRequest);
        if (BooleanUtil.isFalse(updateSuccess)) {
            throw new BusinessException("修改失败,请校验找回密码参数");
        }
        return ServiceResult.newInstanceOfSucResult(AuthResponse.success(findBackPasswordRequest));
    }

    @Override
    protected void executeAfter() {
        // 移除缓存
        captchaService().removeCaptchaVerifyCode(context.getCaptchaCheckRequest());
        mailService().removeMailCode(context.getCheckMailRequest());
    }
}
