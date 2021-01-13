package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录请求类
 */
@Data
@Accessors(chain = true)
public class AuthLoginRequest {
    protected String username;
    protected String password;

    protected UsernamePasswordToken token;

    public AuthLoginRequest build() {
        token = new UsernamePasswordToken(username, password);
        return this;
    }
}
