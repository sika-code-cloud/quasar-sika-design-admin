package com.quasar.sika.design.server.common.shiro.util;

import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 自定义获取Token
 */
@Slf4j
public class ShiroSessionManager extends DefaultWebSessionManager {
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 重写构造器
     */
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    @Override
    protected void onStart(Session session, SessionContext context) {
        super.onStart(session, context);
        if (!WebUtils.isHttp(context)) {
            log.debug("SessionContext argument is not HTTP compatible or does not have an HTTP request/response pair. No session ID cookie will be set.");
        } else {
            HttpServletRequest request = WebUtils.getHttpRequest(context);
            HttpServletResponse response = WebUtils.getHttpResponse(context);
            Serializable sessionId = session.getId();
            this.storeSessionId(sessionId, request, response);
            request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_IS_NEW, Boolean.TRUE);
        }
    }

    /**
     * 把sessionId 放入 response header 中
     * onStart时调用
     * 没有sessionid时 会产生sessionid 并放入 response header中
     */
    private void storeSessionId(Serializable currentId, HttpServletRequest ignored, HttpServletResponse response) {
        if (currentId == null) {
            String msg = "sessionId cannot be null when persisting for subsequent requests.";
            throw new IllegalArgumentException(msg);
        } else {
            String idString = currentId.toString();
            response.setHeader(ShiroConstant.REQUEST_HEADER, idString);
            response.setHeader("Access-Control-Expose-Headers", ShiroConstant.REQUEST_HEADER);
            log.info("Set session ID header for session with id {}", idString);
            log.trace("Set session ID header for session with id {}", idString);
        }
    }

    /**
     * 重写方法实现从请求头获取Token便于接口统一
     * 每次请求进来,Shiro会去从请求头找REQUEST_HEADER这个key对应的Value(Token)
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        Serializable token = WebUtils.toHttp(request).getHeader(ShiroConstant.REQUEST_HEADER);
        // 如果请求头中存在token 则从请求头中获取token
        if (token != null && StringUtils.isNotBlank(token.toString())) {
            //请求头中如果有 authToken, 则其值为sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            //sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            // 否则按默认规则从cookie取token
            token = super.getSessionId(request, response);
        }
        return token;
    }
}
