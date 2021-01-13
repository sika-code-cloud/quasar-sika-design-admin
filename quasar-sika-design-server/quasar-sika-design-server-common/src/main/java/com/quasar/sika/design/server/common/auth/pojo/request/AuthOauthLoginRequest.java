package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 授权tokan登录请求类
 *
 * @author daiqi
 * @create 2021-01-10 0:07
 */
@Data
@Accessors(chain = true)
public class AuthOauthLoginRequest extends AuthLoginRequest {
    private String oauthToken;
    private String source;
}
