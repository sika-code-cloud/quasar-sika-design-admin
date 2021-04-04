package com.quasar.sika.design.server.common.auth.bo.request;

import cn.hutool.core.util.BooleanUtil;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.auth.bo.response.AuthResponseBO;
import com.quasar.sika.design.server.common.auth.domain.AuthDomain;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthForgetPasswordRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.mail.bo.request.checker.CheckMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.shiro.util.SHA256Util;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.executor.StandardExecutorManager;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 找回密码请求逻辑类
 * @date 2021/1/17 22:02
 */
@Data
@Accessors(chain = true)
public class AuthFindBackPasswordRequestBO extends BaseStandardRequestBO<AuthResponseBO> implements AuthDomain {
    private AuthForgetPasswordRequest findBackPasswordRequest;
    private CheckMailRequest checkMailRequest;
    protected CaptchaCheckRequest captchaCheckRequest;
    private CheckMailCodeRequestBO checkMailCodeRequestBO;

    @Override
    protected void init() {
        checkMailCodeRequestBO = new CheckMailCodeRequestBO().setRequest(checkMailRequest);
    }

    @Override
    protected void verify() {
        // 图片验证码校验
        captchaService().checkCaptchaVerifyCode(captchaCheckRequest);
        // 校验邮箱
        authService().checkForgetPasswordEmail(findBackPasswordRequest);
        // 邮箱验证码校验
        StandardExecutorManager.execute(checkMailCodeRequestBO);
    }

    private void initBizData() {
        UserDTO userFromDb = userService().findByEmail(findBackPasswordRequest.getEmail());
        findBackPasswordRequest.setPassword(SHA256Util.sha256(findBackPasswordRequest.getPassword(), userFromDb.getUsername()));
        findBackPasswordRequest.setId(userFromDb.getId());
    }

    @Override
    protected AuthResponseBO doExecute() {
        // 初始化业务逻辑数据
        initBizData();
        // 更新密码
        UserDTO userForUpdate = new UserDTO();
        userForUpdate.setId(findBackPasswordRequest.getId());
        userForUpdate.setPassword(findBackPasswordRequest.getPassword());
        boolean updateSuccess = userService().updateById(findBackPasswordRequest);
        if (BooleanUtil.isFalse(updateSuccess)) {
            throw new BusinessException("修改失败,请校验找回密码参数");
        }
        // 执行完成后
        executeAfter();
        return newResponseBO(AuthResponse.success(findBackPasswordRequest));
    }

    private void executeAfter() {
        // 移除缓存
        captchaService().removeCaptchaVerifyCode(captchaCheckRequest);
        mailService().removeMailCode(checkMailRequest);
    }

    @Override
    public Class<AuthResponseBO> responseClass() {
        return AuthResponseBO.class;
    }
}
