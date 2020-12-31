package com.quasar.sika.design.server.common.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.quasar.sika.design.server.common.service.CommonService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
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
public class CommonServiceImpl implements CommonService {
    @Override
    public AbstractCaptcha createCaptchaVerifyCode() {
        AbstractCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 45);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new RandomGenerator(RandomUtil.BASE_NUMBER, 4));
        // 重新生成code
        captcha.createCode();
        log.info("生成的验证码为：{}", captcha.getCode());
        // 放入session
        ShiroUtils.setAttrbuteToSession("CAPTCHA_CODE", captcha.getCode());
        return captcha;
    }

    @Override
    public void generateAndWriteCaptchaVerifyCodeToResponse(HttpServletResponse response) {
        AbstractCaptcha captcha = createCaptchaVerifyCode();
        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            IoUtil.write(servletOutputStream, true, captcha.getImageBytes());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void checkCaptchaVerifyCode(String clientCode) {
        String captchaCode = ShiroUtils.getAttrbuteFromSession("CAPTCHA_CODE");
        boolean verify = new RandomGenerator(4).verify(captchaCode, clientCode);
        if (!verify) {
            throw new BusinessException("验证码有误");
        }
    }
}
