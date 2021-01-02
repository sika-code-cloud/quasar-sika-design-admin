package com.quasar.sika.design.server.common.auth.service;

import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.BindOauthUserRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.quasar.sika.design.server.common.auth.pojo.response.OauthResponse;
import me.zhyd.oauth.model.AuthCallback;

public interface AuthService {
    /**
     * 注册
     */
    AuthResponse register(AuthRegisterRequest request);

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
     * </pre>
     * @author daiqi
     * @date 2020/12/30 23:16
     * @param source
     * @return java.lang.String
     */
    String getAuthorizeUrl(String source);

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

    /**
     * 绑定授权用户
     *
     * @param request : 绑定授权用户请求对象
     * @return
     */
    OauthResponse bindOauthUser(BindOauthUserRequest request);

}
