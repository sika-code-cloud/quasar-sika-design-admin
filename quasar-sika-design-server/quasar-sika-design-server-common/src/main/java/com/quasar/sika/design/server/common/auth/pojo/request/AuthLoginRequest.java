package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.common.auth.token.ScUsernamePasswordToken;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录请求类
 *
 * @author sikadai
 */
@Data
@Accessors(chain = true)
public class AuthLoginRequest extends ScUsernamePasswordToken {
    public AuthLoginRequest() {
    }

    public AuthLoginRequest(String username, String password) {
        super(username, password);
    }

    public AuthLoginRequest build() {
        return this;
    }
}
