package com.quasar.sika.design.server.common.mail.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 校验邮件验证码请求类
 * @date 2021/1/222:51
 */
@Data
@Accessors(chain = true)
public class CheckMailRequest extends MailCodeRequest{
    private String clientMailCode;
}
