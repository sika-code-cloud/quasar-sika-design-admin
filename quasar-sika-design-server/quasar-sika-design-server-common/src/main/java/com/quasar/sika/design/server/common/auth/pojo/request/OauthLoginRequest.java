package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import com.quasar.sika.design.server.common.auth.token.OauthLoginToken;
import lombok.Data;
import lombok.experimental.Accessors;
import me.zhyd.oauth.model.AuthUser;

/**
 * @author daiqi
 * @create 2020-12-31 0:54
 */
@Data
@Accessors(chain = true)
public class OauthLoginRequest extends AuthLoginRequest {
    private ThirdOauthUserDTO oauthUser;

    @Override
    public AuthLoginRequest build() {
        this.username = oauthUser.getSource() + "-" + oauthUser.getUuid();
        this.password = username;
        token = new OauthLoginToken(username, password).setOauthUser(oauthUser);
        return this;
    }
}
