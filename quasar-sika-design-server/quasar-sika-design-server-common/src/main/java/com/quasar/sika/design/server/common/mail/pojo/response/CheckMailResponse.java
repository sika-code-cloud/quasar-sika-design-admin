package com.quasar.sika.design.server.common.mail.pojo.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送邮箱验证码的相应类
 * @date 2021/1/217:19
 */
@Data
@Accessors(chain = true)
public class CheckMailResponse {
    private String messageId;
}
