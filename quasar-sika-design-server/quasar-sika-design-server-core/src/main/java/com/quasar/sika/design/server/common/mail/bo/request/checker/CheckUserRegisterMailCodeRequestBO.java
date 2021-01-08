package com.quasar.sika.design.server.common.mail.bo.request.checker;

import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.constant.MailCodeEnum;
import com.quasar.sika.design.server.common.mail.pojo.response.CheckMailResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 校验用户注册邮件验证码请求逻辑类
 * @date 2021/1/30:35
 */
@Data
@Accessors(chain = true)
public class CheckUserRegisterMailCodeRequestBO extends CheckMailCodeRequestBO {

    @Override
    protected void init() {
        buildRequest(MailCodeEnum.USER_REGISTER);
    }

    @Override
    protected CheckMailCodeResponseBO doExecute() {
        // 1: 校验验证码
        checkCode();
        return newResponseBO(new CheckMailResponse());
    }

}
