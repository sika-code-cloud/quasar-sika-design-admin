package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 绑定授权用户请求类
 *
 * @author sikadai
 */
@Data
@Accessors(chain = true)
public class BindOauthUserRequest extends AuthLoginRequest {
    private String loginName;

    @Override
    public String getUsername() {
        return this.loginName;
    }
}
