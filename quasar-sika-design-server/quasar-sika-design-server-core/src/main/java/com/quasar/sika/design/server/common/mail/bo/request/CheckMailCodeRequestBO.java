package com.quasar.sika.design.server.common.mail.bo.request;

import com.quasar.sika.design.server.common.mail.bo.reqsponse.CheckMailCodeResponseBO;
import com.quasar.sika.design.server.common.mail.domain.MailDomain;
import com.quasar.sika.design.server.common.mail.pojo.request.CheckMailRequest;
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

    @Override
    public Class<CheckMailCodeResponseBO> responseClass() {
        return CheckMailCodeResponseBO.class;
    }

}
