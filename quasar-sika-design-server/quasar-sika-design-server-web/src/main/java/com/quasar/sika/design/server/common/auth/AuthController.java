package com.quasar.sika.design.server.common.auth;


import com.quasar.sika.design.server.common.auth.factory.AuthFactory;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.BindOauthUserRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.quasar.sika.design.server.common.captcha.service.CaptchaService;
import com.quasar.sika.design.server.common.mail.bo.request.checker.CheckBindOauthUserMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.bo.request.checker.CheckUserRegisterMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.bo.request.sender.SendBindOauthUserMailCodeRequestBO;
import com.quasar.sika.design.server.common.mail.bo.request.sender.SendUserRegisterMailCodeRequestBO;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.base.executor.DomainExecutor;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 认证 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:13
 */
@RestController(value = "authController")
@RequestMapping("auth")
public class AuthController extends BaseStandardController {
    @Autowired
    private AuthService authService;
    @Autowired
    private CaptchaService captchaService;

    /** 授权登录-----begin */
    /**
     * 授权用户绑定-校验验证码
     */
    @RequestMapping("/check_bind_oauth_user_mail_code/anon")
    @ResponseBody
    public Result checkBindOauthUserMailCode(@RequestBody CheckBindOauthUserMailCodeRequestBO requestBO) {
        return success(DomainExecutor.execute(requestBO));
    }

    /**
     * 授权用户绑定-发送验证码
     */
    @RequestMapping("/send_bind_oauth_user_mail_code/anon")
    @ResponseBody
    public Result sendBindOauthUserMailCode(@RequestBody SendBindOauthUserMailCodeRequestBO requestBo) {
        return success(DomainExecutor.execute(requestBo));
    }

    /**
     * 用户注册 - 发送验证码
     */
    @RequestMapping("/check_user_register_mail_code/anon")
    @ResponseBody
    public Result checkUserRegisterMailCode(@RequestBody CheckUserRegisterMailCodeRequestBO requestBo) {
        return success(DomainExecutor.execute(requestBo));
    }

    /**
     * 用户注册 - 校验验证码
     */
    @RequestMapping("/send_user_register_mail_code/anon")
    @ResponseBody
    public Result sendUserRegisterMailCode(@RequestBody SendUserRegisterMailCodeRequestBO requestBo) {
        return success(DomainExecutor.execute(requestBo));
    }

    /**
     * 前端调用接口获取授权URL，并且重定向到该url
     */
    @RequestMapping("/get_authorize_url/{source}/anon")
    @ResponseBody
    public Result getAuthorizeUrl(@PathVariable("source") String source) {
        return success(authService.getAuthorizeUrl(source));
    }

    /**
     * 授权登录-配置的授权回调链接为前端-前端再ajax调用该接口 在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/oauth_login/{source}/anon")
    @ResponseBody
    public Result oauthLogin(@PathVariable("source") String source, AuthCallback callback) {
        return success(authService.oauthLogin(source, callback));
    }

    @RequestMapping("/bind_oauth_user")
    public Result bindOauthUser(BindOauthUserRequest request) {
        return success(authService.bindOauthUser(request));
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
        return new ModelAndView("redirect:/auth/users/anon");
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

        AuthUser user = null;
        if (null == user) {
            return resultGenerator.generateResultError("用户未登录");
        }
        AuthResponse<AuthToken> response = null;
        try {
            response = authRequest.revoke(user.getToken());
            if (response.ok()) {
//                map.remove(user.getUuid());
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

        AuthUser user = null;
        if (null == user) {
            return resultGenerator.generateResultError("用户不存在");
        }
        AuthResponse<AuthToken> response = null;
        try {
            response = authRequest.refresh(user.getToken());
            if (response.ok()) {
                user.setToken(response.getData());
//                map.put(user.getUuid(), user);
                return success("用户 [" + user.getUsername() + "] 的 access token 已刷新！新的 accessToken: " + response.getData().getAccessToken());
            }
            return fail("用户 [" + user.getUsername() + "] 的 access token 刷新失败！" + response.getMsg());
        } catch (AuthException e) {
            return fail(e.getErrorMsg());
        }
    }

    /**
     * 授权登录-----end
     */

    @PostMapping("/register/anon")
    public Result register(@RequestBody AuthRegisterRequest request) {
        return super.success(authService.register(request));
    }

    @PostMapping("/login/anon")
    public Result login(@RequestBody AuthLoginRequest request) {
        return super.success(authService.login(request));
    }

    @PostMapping("/update_current_password")
    public Result updateCurrentPassword(@RequestBody AuthUpdatePasswordRequest request) {
        return super.success(authService.updateCurrentPassword(request));
    }

    @PostMapping("/update_password")
    public Result updatePassword(@RequestBody AuthUpdatePasswordRequest request) {
        return super.success(authService.updatePassword(request));
    }

    @RequestMapping("/current_user")
    public Result currentUser() {
        return super.success(ShiroUtils.getUserInfo());
    }

    @RequestMapping("/get_captcha_verify_code/anon")
    public void getCaptchaVerifyCode(@RequestBody CaptchaGenerateRequest request) {
        CaptchaGenerateRequest doRequest = AuthFactory.loginCaptchaGenerateRequest()
                .setWidth(request.getWidth())
                .setHeight(request.getHeight());
        captchaService.generateAndWriteCaptchaVerifyCodeToResponse(response, doRequest);
    }

    @RequestMapping("/check_captcha_verify_code/anon")
    public Result checkCaptchaVerifyCode(@RequestBody CaptchaCheckRequest request) {
        CaptchaCheckRequest doRequest = AuthFactory.checkLoginCaptchaGenerateRequest()
                .setClientCode(request.getClientCode());
        return success(captchaService.checkCaptchaVerifyCode(doRequest));
    }

    @RequestMapping("/logout")
    public Result logout() {
        ShiroUtils.logout();
        return success("退出系统成功");
    }

    /**
     * 未登录
     */
    @RequestMapping("/unLogin/anon")
    @ResponseBody
    public Result unLogin() {
        return fail(BaseErrorCodeEnum.UN_AUTH, BaseErrorCodeEnum.UN_AUTH.getMessage());
    }

    /**
     * 未授权
     */
    @RequestMapping("/unAuth/anon")
    public Result unAuth() {
        return resultGenerator.generateResultError("当前用户没有授权");
    }

    /**
     * token过期
     */
    @RequestMapping("/tokenExpired/anon")
    public Result tokenExpired() {
        return resultGenerator.generateResultError("token过期，请重新登录");
    }

    /**
     * 被挤下线
     */
    @RequestMapping("/downline/anon")
    public Result downline() {
        return resultGenerator.generateResultError("您的账号已在其他地方登录，被挤下线，请重新登录！");
    }

}
