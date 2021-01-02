package com.quasar.sika.design.server.common.mail.bo.request;

import com.quasar.sika.design.server.common.mail.bo.reqsponse.SendMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public abstract class SendMailCodeRequestBO extends BaseStandardRequestBO<SendMailCodeResponseBO> implements MailDomain {
    protected SendMailRequest request;

    @Override
    protected SendMailCodeResponseBO doExecute() {
        // 发送验证码
        SendMailResponse response = mailService().sendMail(this.request);
        // 缓存请求
        String cacheKey = mailService().getCacheKey(request.getCode());
        mailService().putToCache(cacheKey, request);
        // 响应
        return newResponseBO(response);
    }

    @Override
    public Class<SendMailCodeResponseBO> responseClass() {
        return SendMailCodeResponseBO.class;
    }

}
