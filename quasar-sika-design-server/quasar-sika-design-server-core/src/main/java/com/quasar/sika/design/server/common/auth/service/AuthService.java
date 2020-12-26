package com.quasar.sika.design.server.common.auth.service;

import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthRegisterRequest;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthUpdatePasswordRequest;
import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;

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
}
