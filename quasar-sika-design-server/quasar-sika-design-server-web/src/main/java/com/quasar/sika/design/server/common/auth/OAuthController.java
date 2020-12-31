package com.quasar.sika.design.server.common.auth;

import com.google.common.collect.Maps;
import com.quasar.sika.design.server.common.auth.factory.AuthFactory;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/19 9:28
 * @since 1.8
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController extends BaseStandardController {
    @Autowired
    private AuthService authService;

    private Map<String, AuthUser> map = Maps.newConcurrentMap();

    /**
     * 前端调用接口获取授权URL，并且重定向到该url
     */
    @RequestMapping("/getAuthorizeUrl/{source}/anon")
    @ResponseBody
    public Result getAuthorizeUrl(@PathVariable("source") String source) {
        return success(authService.getAuthorizeUrl(source));
    }

    /**
     * 授权登录-配置的授权回调链接为前端-前端再ajax调用该接口 在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/oauthLogin/{source}/anon")
    @ResponseBody
    public Result oauthLogin(@PathVariable("source") String source, AuthCallback callback) {
        return success(authService.oauthLogin(source, callback));
    }

    @RequestMapping("/render/{source}/anon")
    public void renderAuth(@PathVariable("source") String source) throws IOException {
        response.sendRedirect(authService.getAuthorizeUrl(source));
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/callback/{source}/anon")
    public ModelAndView login(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest request) {
        OauthResponse authResponse = authService.oauthLogin(source, callback);
        return new ModelAndView("redirect:/oauth/users/anon");
    }

    @RequestMapping("/users/anon")
    @ResponseBody
    public Result users() {
        return success(ShiroUtils.getUserInfo());
    }

    @RequestMapping("/revoke/{source}/{uuid}")
    @ResponseBody
    public Result revokeAuth(@PathVariable("source") String source, @PathVariable("uuid") String uuid) throws IOException {
        AuthRequest authRequest = AuthFactory.getAuthRequest(source.toLowerCase());

        AuthUser user = map.get(uuid);
        if (null == user) {
            return resultGenerator.generateResultError("用户未登录");
        }
        AuthResponse<AuthToken> response = null;
        try {
            response = authRequest.revoke(user.getToken());
            if (response.ok()) {
                map.remove(user.getUuid());
                return success("用户 [" + user.getUsername() + "] 的 授权状态 已收回！");
            }
            return resultGenerator.generateResultError("用户 [" + user.getUsername() + "] 的 授权状态 收回失败！" + response.getMsg());
        } catch (AuthException e) {
            return resultGenerator.generateResultError(e.getErrorMsg());
        }
    }

    @RequestMapping("/refresh/{source}/{uuid}")
    @ResponseBody
    public Object refreshAuth(@PathVariable("source") String source, @PathVariable("uuid") String uuid) {
        AuthRequest authRequest = AuthFactory.getAuthRequest(source.toLowerCase());

        AuthUser user = map.get(uuid);
        if (null == user) {
            return resultGenerator.generateResultError("用户不存在");
        }
        AuthResponse<AuthToken> response = null;
        try {
            response = authRequest.refresh(user.getToken());
            if (response.ok()) {
                user.setToken(response.getData());
                map.put(user.getUuid(), user);
                return success("用户 [" + user.getUsername() + "] 的 access token 已刷新！新的 accessToken: " + response.getData().getAccessToken());
            }
            return fail("用户 [" + user.getUsername() + "] 的 access token 刷新失败！" + response.getMsg());
        } catch (AuthException e) {
            return fail(e.getErrorMsg());
        }
    }

}
