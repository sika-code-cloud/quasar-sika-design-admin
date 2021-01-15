package com.quasar.sika.design.server.common.shiro.filter;

import com.quasar.sika.design.server.common.shiro.util.ServletUtils;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.result.generator.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author daiqi
 * @create 2021-01-15 13:17
 */
@Slf4j
public class UserCheckFilter extends UserFilter {
    /**
     * 认证失败回调的方法: 如果登录实体为null就保存请求和跳转登录页面,否则就跳转无权限配置页面
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ResultGenerator resultGenerator = SpringUtil.getBean(ResultGenerator.class);
        // 重定向错误提示处理 - 前后端分离情况下
        ServletUtils.writeForMsg(resultGenerator, WebUtils.toHttp(response), BaseErrorCodeEnum.UN_AUTH, "登录信息失效，请重新登录");
        return false;
    }
}
