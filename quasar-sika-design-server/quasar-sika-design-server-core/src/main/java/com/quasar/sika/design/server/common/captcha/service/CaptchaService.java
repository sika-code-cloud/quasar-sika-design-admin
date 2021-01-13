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

    /**
     * <p>
     * 检验图片验证码 -- 此时不会删除验证码
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     clientCode : 1 : 入参验证码 ：是
     *     type : 10 : 验证码类型 ：是
     * </pre>
     *
     * @param request
     * @return boolean
     * @author daiqi
     * @date 2021/1/9 14:29
     */
    boolean checkCaptchaVerifyCode(CaptchaCheckRequest request);

    /**
     * <p>
     * 检验图片验证码 -- 校验通过会删除验证码
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     clientCode : 1 : 入参验证码 ：是
     *     type : 10 : 验证码类型 ：是
     * </pre>
     *
     * @param request
     * @return boolean
     * @author daiqi
     * @date 2021/1/9 14:29
     */
    boolean checkAndRemoveCaptchaVerifyCode(CaptchaCheckRequest request);
    /**
     * <p>
     *  删除验证码
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     clientCode : 1 : 入参验证码 ：是
     *     type : 10 : 验证码类型 ：是
     * </pre>
     *
     * @param request
     * @return boolean
     * @author daiqi
     * @date 2021/1/9 14:29
     */
    boolean removeCaptchaVerifyCode(CaptchaCheckRequest request);
}
