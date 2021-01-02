package com.quasar.sika.design.server.common.mail.bo.request;

import cn.hutool.core.util.RandomUtil;
import com.quasar.sika.design.server.common.mail.bo.reqsponse.SendMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.sika.code.basic.util.Assert;
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
        request.setSubject(MailCodeEnum.BIND_OAUTH_USER.getDesc());
        request.setCode(MailCodeEnum.BIND_OAUTH_USER.getType());
        request.setContent(RandomUtil.randomNumbers(6));
    }

    @Override
    protected void verify() {
        Assert.verifyObjNull(request, "绑定授权用户请求对象");
        Assert.verifyStrEmpty(request.getSubject(), "绑定授权用户对象");
        Assert.verifyObjNull(request.getCode(), "绑定授权用户编码");
        Assert.verifyStrEmpty(request.getTo(), "绑定授权用户邮箱");
    }

    @Override
    protected SendMailCodeResponseBO doExecute() {
        return super.doExecute();
    }

}
