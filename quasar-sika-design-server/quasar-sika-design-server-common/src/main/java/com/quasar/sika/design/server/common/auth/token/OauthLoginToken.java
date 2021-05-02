package com.quasar.sika.design.server.common.auth.token;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.common.auth.pojo.request.AuthLoginRequest;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 授权登录token
 * @author daiqi
 * @create 2020-12-31 0:49
 */
@Data
@Accessors(chain = true)
public class OauthLoginToken extends AuthLoginRequest {
    private ThirdOauthUserDTO oauthUser;
    private String state;
    public OauthLoginToken() {
    }

    public OauthLoginToken(String username, String password) {
        super(username, password);
    }
}
