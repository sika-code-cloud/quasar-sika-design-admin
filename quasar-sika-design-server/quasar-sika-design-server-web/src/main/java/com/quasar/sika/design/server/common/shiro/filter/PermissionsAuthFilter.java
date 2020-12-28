package com.quasar.sika.design.server.common.shiro.filter;

import com.quasar.sika.design.server.common.shiro.util.ServletUtils;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.result.generator.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PermissionsAuthFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        ResultGenerator resultGenerator = SpringUtil.getBean(ResultGenerator.class);
        String requestUrl = httpRequest.getServletPath();
        log.info("请求的url:  " + requestUrl);

        // 检查是否拥有访问权限
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            // ajax 的请求头里有X-Requested-With: XMLHttpRequest      正常请求没有
            if (ServletUtils.isAjaxReq(httpRequest)) {
                ServletUtils.writeUnPermission(resultGenerator, httpResponse);
            } else {  //正常请求
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }

}
