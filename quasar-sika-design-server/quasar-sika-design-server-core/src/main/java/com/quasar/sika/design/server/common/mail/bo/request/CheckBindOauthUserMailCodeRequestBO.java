package com.quasar.sika.design.server.common.mail.bo.request;

import cn.hutool.core.util.StrUtil;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.request.SendMailRequest;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 发送绑定授权邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class CheckBindOauthUserMailCodeRequestBO extends CheckMailCodeRequestBO {

    @Override
    protected void init() {
        request.setCode(MailCodeEnum.BIND_OAUTH_USER.getType());
    }

    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "绑定授权用户请求对象");
        Assert.verifyStrEmpty(request.getClientMailCode(), "客户端授权验证码");
    }

    @Override
    protected CheckMailCodeResponseBO doExecute() {
        String cacheKey = mailService().getCacheKey(request.getCode());
        SendMailRequest requestFromCatch = getFromCache(cacheKey);
        if (BaseUtil.isNull(requestFromCatch) || StrUtil.isBlank(requestFromCatch.getContent())) {
            throw new BusinessException("授权验证码已失效");
        }
        if (!StrUtil.equalsIgnoreCase(requestFromCatch.getContent(), request.getClientMailCode())) {
            throw new BusinessException("授权验证码有误");
        }
        // 校验整个code码移除缓存
        boolean bool = mailService().removeToCache(cacheKey);
        if(!bool) {
            throw new BusinessException("系统异常");
        }
        return newResponseBO(null);
    }

}
