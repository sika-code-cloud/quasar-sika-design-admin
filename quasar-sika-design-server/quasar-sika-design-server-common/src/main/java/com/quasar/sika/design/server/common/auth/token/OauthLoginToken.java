package com.quasar.sika.design.server.common.auth.token;

import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 授权登录token
 * @author daiqi
 * @create 2020-12-31 0:49
 */
@Data
@Accessors(chain = true)
public class OauthLoginToken extends UsernamePasswordToken {
    private AuthUser authUser;
    public OauthLoginToken() {
    }

    public OauthLoginToken(String username, char[] password) {
        super(username, password);
    }

    public OauthLoginToken(String username, String password) {
        super(username, password);
    }

    public OauthLoginToken(String username, char[] password, String host) {
        super(username, password, host);
    }

    public OauthLoginToken(String username, String password, String host) {
        super(username, password, host);
    }

    public OauthLoginToken(String username, char[] password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public OauthLoginToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public OauthLoginToken(String username, char[] password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

    public OauthLoginToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }
}
