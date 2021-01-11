package com.quasar.sika.design.server.common.shiro.context;

import com.alibaba.fastjson.JSONObject;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.auth.token.OauthLoginToken;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.subject.WebSubjectContext;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;

/**
 * @author daiqi
 * @create 2021-01-11 23:59
 */
@Slf4j
public class CustomerSubjectContext extends DefaultWebSubjectContext implements BaseStandardDomain {
    public CustomerSubjectContext() {
    }

    public CustomerSubjectContext(WebSubjectContext context) {
        super(context);
    }

    @Override
    public Session getSession() {
        log.info("CustomerSubjectContext-----{}", "开始调用自定义的context");
        if (this.getAuthenticationToken() instanceof OauthLoginToken) {
            OauthLoginToken oauthLoginToken = (OauthLoginToken) this.getAuthenticationToken();
            String source = oauthLoginToken.getAuthUser().getSource();
            String state = oauthLoginToken.getState();
            log.info("授权登录的参数为：{}", JSONObject.toJSONString(oauthLoginToken));
            String sessionId = getAuthService().getOauthStateCache(source, state).getClientSessionId();
            log.info("sessionId为：{}", sessionId);
            return ShiroUtils.getSession(sessionId);
        } else {
            return super.getSession();
        }

    }


    private AuthService getAuthService() {
        return getBean(AuthService.class);
    }
}
