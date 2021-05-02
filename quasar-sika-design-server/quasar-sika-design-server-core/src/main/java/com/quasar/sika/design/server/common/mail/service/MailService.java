package com.quasar.sika.design.server.common.mail.service;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.request.MailCodeRequest;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.response.SendMailResponse;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.constant.TypeEnumInf;
import com.sika.code.basic.util.Assert;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;

/**
 * 邮件服务
 *
 * @author sikadai
 */
public interface MailService extends BaseStandardDomain {

    /**
     * 发送邮箱验证码
     *
     * @param request
     * @return SendMailCodeResponse
     */
    SendMailResponse sendMail(SendMailRequest request);

    /**
     * 移除邮箱验证码
     *
     * @param codeRequest
     * @return
     */
    boolean removeMailCode(MailCodeRequest codeRequest);

    /**
     * 设置邮箱验证码
     *
     * @param codeRequest
     * @return
     */
    boolean setMailCode(SendMailRequest codeRequest);

    /**
     * 获取邮箱验证码
     *
     * @param codeRequest
     * @return
     */
    String getMailCode(MailCodeRequest codeRequest);

    /**
     * 获取缓存key
     *
     * @param codeRequest
     * @return
     */
    default String getCacheKey(MailCodeRequest codeRequest) {
        MailCodeEnum mailCodeEnum = TypeEnumInf.findByCode(codeRequest.getCode(), MailCodeEnum.class);
        Assert.verifyObjNullMsg(mailCodeEnum, StrUtil.format("不支持的邮箱编码【{}】", codeRequest.getCode()));
        return "MAIL_CODE:" + this.getClass() + ":" + ShiroUtils.getSessionId() + ":" + mailCodeEnum.name() + ":" + codeRequest.getTo() + ":" + codeRequest.getCode();
    }
}
