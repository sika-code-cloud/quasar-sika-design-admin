package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.common.auth.token.OauthLoginToken;
import lombok.Getter;
import me.zhyd.oauth.model.AuthUser;

/**
 * @author daiqi
 * @create 2020-12-31 0:54
 */
@Getter
public class OauthLoginRequest extends AuthLoginRequest {
    private boolean oauthLogin;
    private AuthUser authUser;
    public OauthLoginRequest build(AuthUser authUser) {
        this.authUser = authUser;
        this.oauthLogin = true;
        return this;
    }

    @Override
    public AuthLoginRequest build() {
        this.username = authUser.getSource() + "-" + authUser.getUuid();
        this.password = username;
        token = new OauthLoginToken(username, password).setAuthUser(authUser);
        return this;
    }
}
