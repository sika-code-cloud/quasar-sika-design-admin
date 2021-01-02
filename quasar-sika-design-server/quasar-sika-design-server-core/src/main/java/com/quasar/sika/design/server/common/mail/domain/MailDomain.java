package com.quasar.sika.design.server.common.mail.domain;

import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.mail.service.MailService;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/31:34
 */
public interface MailDomain extends BaseStandardDomain {

    default UserService userService() {
        return getBean(UserService.class);
    }

    default MailService mailService() {
        return getBean(MailService.class);
    }
}
