package com.quasar.sika.design.server.common.auth.bo.response;

import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sikadai
 * @Description:
 * @date 2021/1/1722:03
 */
@Data
@Accessors(chain = true)
public class AuthResponseBO implements BaseStandardResponseBO<AuthResponse> {
    private AuthResponse response;
    @Override
    public void build(AuthResponse authResponse) {
        this.response = authResponse;
    }
}
