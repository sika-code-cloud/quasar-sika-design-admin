package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthLoginRequest {
    private String username;
    private String password;
}
