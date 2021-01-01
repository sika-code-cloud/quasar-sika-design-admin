package com.quasar.sika.design.server.common.captcha.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeTypeEnum;
import com.quasar.sika.design.server.common.captcha.factory.CaptchaFactory;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.quasar.sika.design.server.common.captcha.service.CaptchaService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public AbstractCaptcha createCaptchaVerifyCode(CaptchaGenerateRequest request) {
        AbstractCaptcha captcha = CaptchaFactory.createCaptchaVerifyCode(request);
        log.info("生成的验证码为：{}", captcha.getCode());
        // 放入缓存
        putCaptchaCodeToCache(request.getType(), captcha.getCode());
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
        String captchaCode = getCaptchaCodeFromCache(request.getType());
        if (StrUtil.isBlank(captchaCode)) {
            throw new BusinessException("验证码已失效");
        }
        CodeGenerator generator = CaptchaFactory.createCodeGenerator(request);
        boolean verify = generator.verify(captchaCode, request.getClientCode());
        if (verify && removeCaptchaCodeToCache(request.getType())) {
            return true;
        }
        throw new BusinessException("验证码有误");
    }

    private String getCaptchaCodeFromCache(Integer type) {
        String captchaKey = getCaptchaKey(type);
        return (String) redisTemplate.opsForValue().get(captchaKey);
    }

    private void putCaptchaCodeToCache(Integer type, String captchaCode) {
        String captchaKey = getCaptchaKey(type);
        CaptchaCodeTypeEnum codeTypeEnum = TypeEnumInf.find(type, CaptchaCodeTypeEnum.class);
        redisTemplate.opsForValue().set(captchaKey, captchaCode, codeTypeEnum.getTimeout(), codeTypeEnum.getTimeUnit());
    }

    private Boolean removeCaptchaCodeToCache(Integer type) {
        String captchaKey = getCaptchaKey(type);
        return redisTemplate.delete(captchaKey);
    }

    private String getCaptchaKey(Integer type) {
        CaptchaCodeTypeEnum codeTypeEnum = TypeEnumInf.find(type, CaptchaCodeTypeEnum.class);
        return "CAPTCHA_CODE:" + this.getClass() + ":" + ShiroUtils.getSessionId() + ":" + codeTypeEnum.name();
    }

}
