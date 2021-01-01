package com.quasar.sika.design.server.common.captcha.factory;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeGeneratorEnum;
import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeStyleEnum;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.sika.code.basic.constant.TypeEnumInf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片验证码工厂类
 */
public class CaptchaFactory {
    private static final Logger log = LoggerFactory.getLogger(CaptchaFactory.class);
    public static AbstractCaptcha createCaptchaVerifyCode(CaptchaGenerateRequest captchaGenerateRequest) {
        captchaGenerateRequest.build();
        AbstractCaptcha captcha = createAbstractCaptcha(captchaGenerateRequest);
        // 自定义验证码内容
        captcha.setGenerator(createCodeGenerator(captchaGenerateRequest));
        // 重新生成code
        captcha.createCode();
        return captcha;
    }

    private static AbstractCaptcha createAbstractCaptcha(CaptchaGenerateRequest captchaGenerateRequest) {
        CaptchaCodeStyleEnum codeStyleEnum = TypeEnumInf.find(captchaGenerateRequest.getStyle(), CaptchaCodeStyleEnum.class);
        if (codeStyleEnum == null) {
            codeStyleEnum = CaptchaCodeStyleEnum.LINE_CAPTCHA;
        }
        AbstractCaptcha captcha;
        Integer width = captchaGenerateRequest.getWidth();
        Integer height = captchaGenerateRequest.getHeight();
        if (CaptchaCodeStyleEnum.LINE_CAPTCHA.equals(codeStyleEnum)) {
            captcha = CaptchaUtil.createLineCaptcha(width, height, 0, 50);
        } else if (CaptchaCodeStyleEnum.CIRCLE_CAPTCHA.equals(codeStyleEnum)) {
            captcha = CaptchaUtil.createCircleCaptcha(width, height,0, 6);
        } else if (CaptchaCodeStyleEnum.SHEAR_CAPTCHA.equals(codeStyleEnum)) {
            captcha = CaptchaUtil.createShearCaptcha(width, height);
        } else {
            throw new RuntimeException("不支持的验证码样式");
        }
        return captcha;
    }

    public static CodeGenerator createCodeGenerator(CaptchaGenerateRequest captchaGenerateRequest) {
        Integer codeGeneratorType = captchaGenerateRequest.getGenerator();
        Integer length = captchaGenerateRequest.getLength();
        return createCodeGenerator(codeGeneratorType, length);
    }

    public static CodeGenerator createCodeGenerator(Integer codeGeneratorType, Integer length) {
        CaptchaCodeGeneratorEnum codeGeneratorEnum = TypeEnumInf.find(codeGeneratorType, CaptchaCodeGeneratorEnum.class);
        if (codeGeneratorEnum == null) {
            throw new RuntimeException(StrUtil.format("不支持的验证码生产者【{}】", codeGeneratorType));
        }
        CodeGenerator codeGenerator = null;
        if (CaptchaCodeGeneratorEnum.RANDOM_GENERATOR.equals(codeGeneratorEnum)) {
            codeGenerator = new RandomGenerator(length);
        } else if (CaptchaCodeGeneratorEnum.RANDOM_GENERATOR_NUMBER.equals(codeGeneratorEnum)) {
            codeGenerator = new RandomGenerator(RandomUtil.BASE_NUMBER, length);
        } else if (CaptchaCodeGeneratorEnum.RANDOM_GENERATOR_CHARACTER.equals(codeGeneratorEnum)) {
            codeGenerator = new RandomGenerator(RandomUtil.BASE_CHAR, length);
        } else if (CaptchaCodeGeneratorEnum.MATH_GENERATOR.equals(codeGeneratorEnum)) {
            codeGenerator = new MathGenerator(length);
        } else {
            throw new RuntimeException("不支持的验证码生产者");
        }
        return codeGenerator;
    }
}
