package com.quasar.sika.design.server.common.captcha.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
public enum CaptchaCodeTypeEnum implements TypeEnumInf<Integer> {
    /** 图片验证码类型 */
    LOGIN(10, "登录验证码"),
    REGISTER(20, "注册验证码"),
    FORGET_PASSWORD(30, "忘记密码验证码"),
    ;
    private final Integer type;
    private final String desc;
    private final Long timeout = 600L;
    private final TimeUnit timeUnit = TimeUnit.SECONDS;
}
