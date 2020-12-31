package com.quasar.sika.design.server.common.auth.pojo.response;

import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * 授权登录响应类
 *
 * @author daiqi
 * @create 2020-12-30 23:19
 */
@Data
@Accessors(chain = true)
public class OauthResponse extends AuthResponse {
    private AuthUser authUser;
}
