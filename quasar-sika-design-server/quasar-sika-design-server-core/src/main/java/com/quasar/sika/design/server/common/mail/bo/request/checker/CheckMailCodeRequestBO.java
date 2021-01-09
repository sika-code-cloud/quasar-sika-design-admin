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
        request.setType(mailCodeEnum.getType());
        request.setCode(mailCodeEnum.getCode());
    }

    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "邮箱请求对象");
        Assert.verifyStrEmpty(request.getClientMailCode(), "客户端验证码");
    }

    @Override
    public Class<CheckMailCodeResponseBO> responseClass() {
        return CheckMailCodeResponseBO.class;
    }

    /**
     * 校验验证码 -- 不删除缓存
     */
    protected SendMailRequest checkCode() {
        String cacheKey = mailService().getCacheKey(request);
        SendMailRequest requestFromCatch = getFromCache(cacheKey);
        if (BaseUtil.isNull(requestFromCatch) || StrUtil.isBlank(requestFromCatch.getContent())) {
            throw new BusinessException("邮箱验证码已失效");
        }
        if (!StrUtil.equalsIgnoreCase(requestFromCatch.getContent(), request.getClientMailCode())) {
            throw new BusinessException("邮箱验证码有误");
        }
        return requestFromCatch;
    }

    /**
     * 校验验证码 --- 通过后删除
     */
    protected SendMailRequest checkCodeAndRemove() {
        SendMailRequest requestFromCatch = checkCode();
        // code码移除缓存
        removeCode();
        return requestFromCatch;
    }

    public void removeCode() {
        // code码移除缓存
        String cacheKey = mailService().getCacheKey(request);
        boolean bool = mailService().removeToCache(cacheKey);
        if (!bool) {
            throw new BusinessException("系统异常");
        }
    }

}
