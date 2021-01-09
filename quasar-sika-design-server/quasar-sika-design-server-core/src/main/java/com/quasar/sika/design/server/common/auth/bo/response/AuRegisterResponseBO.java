package com.quasar.sika.design.server.common.auth.bo.response;

import com.quasar.sika.design.server.common.auth.pojo.response.AuthResponse;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-09 18:02
 */
@Data
@Accessors(chain = true)
public class AuRegisterResponseBO implements BaseStandardResponseBO<AuthResponse> {
    private AuthResponse response;
    @Override
    public void build(AuthResponse authResponse) {
        this.response = authResponse;
    }
}
