package com.quasar.sika.design.server.common.mail.bo.request.checker;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardRequestBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/31:30
 */
@Data
@Accessors(chain = true)
public abstract class CheckMailCodeRequestBO extends BaseStandardRequestBO<CheckMailCodeResponseBO> implements MailDomain {
    protected CheckMailRequest request;

    protected void buildRequest(MailCodeEnum mailCodeEnum) {
        request.setType(MailCodeEnum.BIND_OAUTH_USER.getType());
        request.setCode(MailCodeEnum.BIND_OAUTH_USER.getCode());
    }
    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "用户请求对象");
        Assert.verifyStrEmpty(request.getClientMailCode(), "客户端验证码");
    }

    @Override
    public Class<CheckMailCodeResponseBO> responseClass() {
        return CheckMailCodeResponseBO.class;
    }

    protected SendMailRequest checkCode() {
        String cacheKey = mailService().getCacheKey(request.getCode());
        SendMailRequest requestFromCatch = getFromCache(cacheKey);
        if (BaseUtil.isNull(requestFromCatch) || StrUtil.isBlank(requestFromCatch.getContent())) {
            throw new BusinessException("授权验证码已失效");
        }
        if (!StrUtil.equalsIgnoreCase(requestFromCatch.getContent(), request.getClientMailCode())) {
            throw new BusinessException("授权验证码有误");
        }
        // code码移除缓存
        boolean bool = mailService().removeToCache(cacheKey);
        if (!bool) {
            throw new BusinessException("系统异常");
        }
        return requestFromCatch;
    }

}
