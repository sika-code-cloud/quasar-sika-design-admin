package com.quasar.sika.design.server.common.mail.service;

import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.SendMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.bo.request.CheckMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.bo.request.SendMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;

/**
 * 邮件服务
 *
 * @author sikadai
 */
public interface MailService extends BaseStandardDomain {
    /**
     * 校验邮箱验证码
     *
     * @param request
     * @return CheckMailCodeResponsetBO
     */
    CheckMailCodeResponseBO checkMailCode(CheckMailCodeRequestBO requestBO);

    SendMailCodeResponseBO sendMailCode(SendMailCodeRequestBO requestBO);

    /**
     * 发送邮箱验证码
     *
     * @param request
     * @return SendMailCodeResponse
     */
    SendMailResponse sendMail(SendMailRequest request);

    /**
     * 获取缓存key
     * @param code
     * @return
     */
    default String getCacheKey(String code) {
        MailCodeEnum mailCodeEnum = TypeEnumInf.find(code, MailCodeEnum.class);
        return "MAIL_CODE:" + this.getClass() + ":" + ShiroUtils.getSessionId() + ":" + mailCodeEnum.name();
    }
}
