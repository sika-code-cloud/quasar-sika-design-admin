package com.quasar.sika.design.server.common.captcha.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CaptchaCodeGeneratorEnum implements TypeEnumInf {
    RANDOM_GENERATOR(10, "随机验证码生产者数据+字母"),
    RANDOM_GENERATOR_NUMBER(11, "纯数字随机验证码生产者"),
    RANDOM_GENERATOR_CHARACTER(12, "纯字母随机验证码生产者"),
    MATH_GENERATOR(20, "数学运算验证码生产者")
    ;
    private final Integer type;
    private final String desc;
}
