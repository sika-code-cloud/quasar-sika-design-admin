package com.quasar.sika.design.server.common.shiro.filter;

import cn.hutool.core.util.ObjectUtil;
import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.shiro.constant.ShiroConstant;
import com.quasar.sika.design.server.common.shiro.util.ServletUtils;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.result.generator.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TokenCheckFilter extends UserFilter {
    @Autowired
    ResultGenerator resultGenerator;

    /**
     * 判断是否拥有权限 true:认证成功  false:认证失败
     * mappedValue 访问该url时需要的权限
     * subject.isPermitted 判断访问的用户是否拥有mappedValue权限
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 根据请求头拿到token
        String token = WebUtils.toHttp(request).getHeader(ShiroConstant.REQUEST_HEADER);
        log.info("浏览器token：" + token);
        UserDTO userInfo = ShiroUtils.getUserInfo();
        String userToken = userInfo.getToken();
        // 检查token是否过期
        if (ObjectUtil.notEqual(token, userToken)) {
            return false;
        }
        return true;
    }

    /**
     * 认证失败回调的方法: 如果登录实体为null就保存请求和跳转登录页面,否则就跳转无权限配置页面
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        UserDTO userInfo = ShiroUtils.getUserInfo();
        // 重定向错误提示处理 - 前后端分离情况下
        ServletUtils.writeUnPermission(resultGenerator, WebUtils.toHttp(response));
        return false;
    }

}
