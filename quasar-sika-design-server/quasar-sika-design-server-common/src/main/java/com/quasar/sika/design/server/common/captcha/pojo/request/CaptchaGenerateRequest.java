package com.quasar.sika.design.server.common.captcha.pojo.request;

import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeGeneratorEnum;
import com.quasar.sika.design.server.common.captcha.constant.CaptchaCodeStyleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CaptchaGenerateRequest {
    private Integer width;
    private Integer height;
    private Integer style;
    private Integer length;
    private Integer generator;

    private Integer type;

    public CaptchaGenerateRequest build() {
        if (this.length == null) {
            this.length = 4;
        }
        if (this.style == null) {
            this.style = CaptchaCodeStyleEnum.LINE_CAPTCHA.getType();
        }
        buildCaptchaGenerateRequest();
        return this;
    }

    protected void buildCaptchaGenerateRequest() {
        // 算数验证码只需要设置长度为1
        this.generator = CaptchaCodeGeneratorEnum.MATH_GENERATOR.getType();
        this.style = CaptchaCodeStyleEnum.LINE_CAPTCHA.getType();
        this.length = 1;
    }
}
