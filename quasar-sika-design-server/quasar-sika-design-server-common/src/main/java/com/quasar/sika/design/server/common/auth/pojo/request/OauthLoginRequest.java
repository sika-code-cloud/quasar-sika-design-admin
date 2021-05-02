package com.quasar.sika.design.server.common.auth.pojo.request;

import com.quasar.sika.design.server.business.thirdoauthuser.pojo.dto.ThirdOauthUserDTO;
import lombok.Data;
import lombok.experimental.Accessors;

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
        return this;
    }
}
