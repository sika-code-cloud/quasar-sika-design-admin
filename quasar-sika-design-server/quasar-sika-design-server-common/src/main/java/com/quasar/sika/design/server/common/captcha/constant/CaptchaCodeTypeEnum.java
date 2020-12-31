package com.quasar.sika.design.server.common.captcha.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaptchaCodeTypeEnum implements TypeEnumInf {
    LOGIN(10, "登录验证码"),
    ;
    private final Integer type;
    private final String desc;
}
