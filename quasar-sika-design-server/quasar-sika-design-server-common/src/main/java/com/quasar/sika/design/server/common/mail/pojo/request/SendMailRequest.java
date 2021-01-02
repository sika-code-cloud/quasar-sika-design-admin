package com.quasar.sika.design.server.common.mail.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送邮箱验证码请求
 * @date 2021/1/217:18
 */
@Data
@Accessors(chain = true)
public class SendMailRequest extends MailCodeRequest{
    private String to;
    private String subject;
    private String content;
    private boolean isHtml;
}
