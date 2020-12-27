package com.quasar.sika.design.server.common.auth;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.quasar.sika.design.server.business.user.service.UserService;
import com.quasar.sika.design.server.common.auth.cache.AuthStateRedisCache;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.xkcoding.http.config.HttpConfig;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
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
    private AuthStateRedisCache stateRedisCache;
    @Autowired
    private UserService userService;
    private Map<String, AuthUser> map = Maps.newConcurrentMap();

    @RequestMapping("/render/{source}")
    @ResponseBody
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        log.info("进入render：" + source);
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        log.info(authorizeUrl);
        response.sendRedirect(authorizeUrl);
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public ModelAndView login(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest request) {
        log.info("进入callback：" + source + " callback params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        log.info(JSONObject.toJSONString(response));

        if (response.ok()) {
            map.put(response.getData().getUuid(), response.getData());
            return new ModelAndView("redirect:/oauth/users");
        }

        Map<String, Object> map = new HashMap<>(1);
        map.put("errorMsg", response.getMsg());

        return new ModelAndView("error", map);
    }
    @RequestMapping("/users")
    @ResponseBody
    public Result users() {
        return success(map);
    }
    @RequestMapping("/revoke/{source}/{uuid}")
    @ResponseBody
    public Result revokeAuth(@PathVariable("source") String source, @PathVariable("uuid") String uuid) throws IOException {
        AuthRequest authRequest = getAuthRequest(source.toLowerCase());

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
        AuthRequest authRequest = getAuthRequest(source.toLowerCase());

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
            return error("用户 [" + user.getUsername() + "] 的 access token 刷新失败！" + response.getMsg());
        } catch (AuthException e) {
            return error(e.getErrorMsg());
        }
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source
     * @return
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source.toLowerCase()) {
            case "dingtalk":
                authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                        .clientId("dingoa7ejqhlogze0ez7s3")
                        .clientSecret("EbUe0q3iTvMDEkQoaWRwYe-H2h9QJobwE4TDoTyO0jzvV4SwovH5Ltaziu-HWmcx")
                        .redirectUri("http://localhost:8443/oauth/callback/dingtalk")
                        .build());
                break;
            case "baidu":
                authRequest = new AuthBaiduRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .httpConfig(HttpConfig.builder().timeout(15000).build())
                        .clientId("a60f78e6b3f5dcd39510")
                        .clientSecret("1ecd2e3c400d8c8b5cbd3566ab4950d1fddede79")
                        .redirectUri("http://localhost:8101/oauth/callback/github")
                        .build(), stateRedisCache);
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("427f193cd6a3319e8f84570a21cecc563ed80c4cd4d46e647312306fa7d74329")
                        .clientSecret("4ec7e3ab06fbda68414bc250a28858ec51f6e6e888f692760db3c1cc578e0900")
                        .redirectUri("http://127.0.0.1:8101/oauth/callback/gitee")
                        .build(), stateRedisCache);
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "coding":
                authRequest = new AuthCodingRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .codingGroupName("")
                        .build());
                break;
            case "oschina":
                authRequest = new AuthOschinaRequest(AuthConfig.builder()
                        .clientId("4iNACRB7hVRkdPiOMsvj")
                        .clientSecret("1tWgSM7VMnnzc7ZIWjIvoQJ857b6oOuj")
                        .redirectUri("http://localhost:8443/oauth/callback/oschina")
                        .build());
                break;
            case "alipay":
                // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .alipayPublicKey("")
                        .redirectUri("")
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "wechat_open":
                authRequest = new AuthWeChatOpenRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "taobao":
                authRequest = new AuthTaobaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "google":
                authRequest = new AuthGoogleRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "facebook":
                authRequest = new AuthFacebookRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "douyin":
                authRequest = new AuthDouyinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "linkedin":
                authRequest = new AuthLinkedinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "microsoft":
                authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "mi":
                authRequest = new AuthMiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "teambition":
                authRequest = new AuthTeambitionRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "pinterest":
                authRequest = new AuthPinterestRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "renren":
                authRequest = new AuthRenrenRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "stack_overflow":
                authRequest = new AuthStackOverflowRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .stackOverflowKey("")
                        .build());
                break;
            case "huawei":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "wechat_enterprise":
                authRequest = new AuthWeChatEnterpriseRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .agentId("")
                        .build());
                break;
            case "kujiale":
                authRequest = new AuthKujialeRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "gitlab":
                authRequest = new AuthGitlabRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "meituan":
                authRequest = new AuthMeituanRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "eleme":
                authRequest = new AuthElemeRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "twitter":
                authRequest = new AuthTwitterRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build())
                        .build());
                break;
            case "wechat_mp":
                authRequest = new AuthWeChatMpRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            case "aliyun":
                authRequest = new AuthAliyunRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("")
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }
}
