package com.quasar.sika.design.server.common.mail.bo.response;

import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.response.CheckMailResponse;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description: 校验邮件验证码响应逻辑类
 * @date 2021/1/31:37
 */
@Data
@Accessors(chain = true)
public class CheckMailCodeResponseBO implements BaseStandardResponseBO<CheckMailResponse>, MailDomain {
    private CheckMailResponse response;
    @Override
    public void build(CheckMailResponse response) {
        this.response = response;
    }
}
