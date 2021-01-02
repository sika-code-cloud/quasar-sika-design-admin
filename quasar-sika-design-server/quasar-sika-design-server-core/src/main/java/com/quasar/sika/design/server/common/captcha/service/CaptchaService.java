package com.quasar.sika.design.server.common.captcha.service;

import cn.hutool.captcha.AbstractCaptcha;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;

import javax.servlet.http.HttpServletResponse;

/**
 * @author daiqi
 * @create 2020-12-31 18:19
 */
public interface CaptchaService extends BaseStandardDomain {
    /**
     * 创建图片验证码
     */
    AbstractCaptcha createCaptchaVerifyCode(CaptchaGenerateRequest request);

    void generateAndWriteCaptchaVerifyCodeToResponse(HttpServletResponse response, CaptchaGenerateRequest request);

    boolean checkCaptchaVerifyCode(CaptchaCheckRequest request);
}
