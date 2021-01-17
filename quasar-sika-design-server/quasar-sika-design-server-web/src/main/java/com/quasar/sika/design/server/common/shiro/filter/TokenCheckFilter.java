package com.quasar.sika.design.server.common.shiro.filter;

import cn.hutool.core.util.ObjectUtil;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.string.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class TokenCheckFilter extends UserCheckFilter {
    /**
     * 判断是否拥有权限 true:认证成功  false:认证失败
     * mappedValue 访问该url时需要的权限
     * subject.isPermitted 判断访问的用户是否拥有mappedValue权限
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (WebUtils.toHttp(request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        // 根据请求头拿到token
        String token = WebUtils.toHttp(request).getHeader(ShiroConstant.REQUEST_HEADER);
        log.info("浏览器token：" + token);
        if (StringUtil.isBlank(token)) {
            return false;
        }
        UserDTO userInfo = ShiroUtils.getUserInfo();
        if (BaseUtil.isNull(userInfo)) {
            return false;
        }
        String userToken = ShiroUtils.getSessionId();
        // 检查token是否过期
        if (ObjectUtil.notEqual(token, userToken)) {
            return false;
        }
        return true;
    }
}
