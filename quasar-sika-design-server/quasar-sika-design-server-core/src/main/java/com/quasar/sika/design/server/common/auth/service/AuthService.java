package com.quasar.sika.design.server.common.auth.service;

import com.quasar.sika.design.server.common.auth.pojo.dto.OauthStateCacheDTO;
import com.quasar.sika.design.server.common.auth.pojo.request.*;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import me.zhyd.oauth.model.AuthCallback;

public interface AuthService {
    /**
     * <p>
     * 获取当前用户
     * </p>
     *
     * @return com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse
     * @author daiqi
     * @date 2021/1/18 23:46
     */
    AuthResponse currentUser();

    /**
     * 登录
     */
    AuthResponse loginPhone(AuthLoginPhoneRequest request);

    /**
     * 登录
     */
    AuthResponse login(AuthLoginRequest request);

    /**
     * 修改当前用户密码
     */
    AuthResponse updateCurrentPassword(AuthUpdatePasswordRequest request);

    /**
     * 修改用户密码
     */
    AuthResponse updatePassword(AuthUpdatePasswordRequest request);

    /***
     * <p>
     * 获取授权URL
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     source : gitee : 授权登录的source字符串，参考AuthDefaultSource ： 是 参考
     *     clientUrl : localhost : 前端当前页面的url ： 是 参考
     * </pre>
     * @author daiqi
     * @date 2020/12/30 23:16
     * @param source
     * @return java.lang.String
     */
    String getAuthorizeUrl(String source, String clientUrl);

    /**
     * <p>
     * 授权登录
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     source : gitee : 授权登录的source字符串，参考AuthDefaultSource ： 是 参考
     *     callback : ： 第三方授权回调的参数 ： 是
     * </pre>
     *
     * @param source
     * @param callback
     * @return com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse
     * @author daiqi
     * @date 2020/12/30 23:21
     */
    OauthResponse oauthLogin(String source, AuthCallback callback);

    AuthResponse doOauthLogin(AuthOauthLoginRequest request);

    /**
     * 绑定授权用户
     *
     * @param request : 绑定授权用户请求对象
     * @return
     */
    AuthResponse bindOauthUser(AuthLoginRequest request);

    /**
     * 校验忘记密码的邮箱
     *
     * @param request
     * @return
     * @author : sikadai
     */
    boolean checkForgetPasswordEmail(AuthForgetPasswordRequest request);

    /**
     * 校验注册的邮箱
     */
    boolean checkRegisterEmail(AuthRegisterRequest registerRequest);

    /**
     * 校验注册的手机号
     */
    boolean checkRegisterPhone(AuthRegisterRequest registerRequest);

    /**
     * 校验注册用户名
     */
    boolean checkRegisterUsername(AuthRegisterRequest registerRequest);

    /**
     * <p>
     * 获取授权登录state对应的缓存值
     * </p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     source : github : 登录来源 ： 是
     *     state : rtrt : 授权登录成功后返回的state ： 是
     * </pre>
     *
     * @param source
     * @param state
     * @return com.quasar.sika.design.server.common.auth.pojo.dto.OauthStateCacheDTO
     * @author daiqi
     * @date 2021/1/12 0:48
     */
    OauthStateCacheDTO getOauthStateCache(String source, String state);

}
