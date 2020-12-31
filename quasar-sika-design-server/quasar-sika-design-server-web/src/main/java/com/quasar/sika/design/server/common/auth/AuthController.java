package com.quasar.sika.design.server.common.auth;


import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.service.AuthService;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaCheckRequest;
import com.quasar.sika.design.server.common.captcha.pojo.request.CaptchaGenerateRequest;
import com.quasar.sika.design.server.common.captcha.service.CaptchaService;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        captchaService.generateAndWriteCaptchaVerifyCodeToResponse(response, request);
    }

    @RequestMapping("/check_captcha_verify_code/anon")
    public Result checkCaptchaVerifyCode(@RequestBody CaptchaCheckRequest request) {
        captchaService.checkCaptchaVerifyCode(request);
        return success("校验成功");
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
        return fail(BaseErrorCodeEnum.RUNTIME_EXCEPTION, "用户尚未登录，请先登录");
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
