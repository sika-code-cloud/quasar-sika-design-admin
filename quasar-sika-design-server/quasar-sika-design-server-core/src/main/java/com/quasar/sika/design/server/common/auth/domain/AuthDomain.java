package com.quasar.sika.design.server.common.auth.domain;

import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.captcha.service.CaptchaService;
import com.quasar.sika.design.server.common.mail.service.MailService;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;

/**
 * @author daiqi
 * @create 2021-01-09 18:03
 */
public interface AuthDomain extends BaseStandardDomain {
    default UserService userService() {
        return getBean(UserService.class);
    }

    default AuthService authService() {
        return getBean(AuthService.class);
    }

    default MailService mailService() {
        return getBean(MailService.class);
    }

    default CaptchaService captchaService() {
        return getBean(CaptchaService.class);
    }
}
