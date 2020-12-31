package com.quasar.sika.design.server.common.service;

import cn.hutool.captcha.AbstractCaptcha;

import javax.servlet.http.HttpServletResponse;

/**
 * @author daiqi
 * @create 2020-12-31 18:19
 */
public interface CommonService {
    /**
     * 创建图片验证码
     */
    AbstractCaptcha createCaptchaVerifyCode();

    void generateAndWriteCaptchaVerifyCodeToResponse(HttpServletResponse response);

    void checkCaptchaVerifyCode(String clientCode);
}
