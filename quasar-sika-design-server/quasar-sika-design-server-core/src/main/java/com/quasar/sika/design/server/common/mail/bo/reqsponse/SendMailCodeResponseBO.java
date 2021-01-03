package com.quasar.sika.design.server.common.mail.bo.reqsponse;

import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送邮件验证码响应业务逻辑类
 * @date 2021/1/30:36
 */
@Data
@Accessors(chain = true)
public class SendMailCodeResponseBO implements BaseStandardResponseBO<SendMailResponse>, MailDomain {
    private SendMailResponse response;
    @Override
    public void build(SendMailResponse response) {
        this.response = response;
    }
}
