package com.quasar.sika.design.server.common.captcha.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.io.IoUtil;
import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeTypeEnum;
import com.quasar.sika.design.server.common.captcha.factory.CaptchaFactory;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.quasar.sika.design.server.common.captcha.service.CaptchaService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author daiqi
 * @create 2020-12-31 18:20
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Override
    public AbstractCaptcha createCaptchaVerifyCode(CaptchaGenerateRequest request) {
        AbstractCaptcha captcha = CaptchaFactory.createCaptchaVerifyCode(request);
        log.info("生成的验证码为：{}", captcha.getCode());
        // 放入session
        ShiroUtils.setAttributeToSession(getCaptchaKey(request.getType()), captcha.getCode());
        return captcha;
    }

    @Override
    public void generateAndWriteCaptchaVerifyCodeToResponse(HttpServletResponse response, CaptchaGenerateRequest request) {
        AbstractCaptcha captcha = createCaptchaVerifyCode(request);
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            IoUtil.write(servletOutputStream, true, captcha.getImageBytes());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkCaptchaVerifyCode(CaptchaCheckRequest request) {
        String captchaCode = ShiroUtils.getAttrbuteFromSession(getCaptchaKey(request.getType()));
        CodeGenerator generator = CaptchaFactory.createCodeGenerator(request.getGenerator(), request.getCleatCode().length());
        boolean verify = generator.verify(captchaCode, request.getCleatCode());
        if (verify) {
            return true;
        }
        throw new BusinessException("验证码有误");
    }

    private String getCaptchaKey(Integer type) {
        CaptchaCodeTypeEnum codeTypeEnum = TypeEnumInf.find(type, CaptchaCodeTypeEnum.class);
        return "CAPTCHA_CODE:" + this.getClass() + ":" + codeTypeEnum.name();
    }
}
