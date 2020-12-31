package com.quasar.sika.design.server.common.captcha.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CaptchaCheckRequest {
    private String cleatCode;
    private Integer type;
    private Integer generator;
}
