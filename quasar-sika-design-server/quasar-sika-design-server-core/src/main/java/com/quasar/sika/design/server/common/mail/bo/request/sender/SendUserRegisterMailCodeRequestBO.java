package com.quasar.sika.design.server.common.mail.bo.request.sender;

import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送用户注册邮件验证码
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class SendUserRegisterMailCodeRequestBO extends SendMailCodeRequestBO {

    @Override
    protected void init() {
        super.buildRequest(MailCodeEnum.USER_REGISTER);
    }

}
