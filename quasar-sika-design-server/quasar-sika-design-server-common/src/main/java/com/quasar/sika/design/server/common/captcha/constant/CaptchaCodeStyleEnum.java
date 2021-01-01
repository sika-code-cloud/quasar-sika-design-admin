package com.quasar.sika.design.server.common.captcha.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaptchaCodeStyleEnum implements TypeEnumInf {
    CIRCLE_CAPTCHA(10, "圆圈干扰的图片验码"),
    LINE_CAPTCHA(20, "线性干扰的图片验码"),
    SHEAR_CAPTCHA(30, "扭曲干扰的图片验码"),
    ;
    private final Integer type;
    private final String desc;


}
