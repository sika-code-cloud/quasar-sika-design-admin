package com.quasar.sika.design.server.common.mail.bo.request.sender;

import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送绑定授权邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class SendBindOauthUserMailCodeRequestBO extends SendMailCodeRequestBO {

    @Override
    protected void init() {
        super.buildRequest(MailCodeEnum.BIND_OAUTH_USER);
    }

}
