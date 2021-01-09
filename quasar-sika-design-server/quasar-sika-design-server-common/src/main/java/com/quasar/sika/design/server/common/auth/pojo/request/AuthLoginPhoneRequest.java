package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2021-01-10 0:07
 */
@Data
@Accessors(chain = true)
public class AuthLoginPhoneRequest extends AuthLoginRequest{
    private String phone;
}
