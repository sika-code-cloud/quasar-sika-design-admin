package com.quasar.sika.design.server.common.auth.pojo.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthUpdatePasswordRequest {
    private Long userId;
    private String username;
    private String password;
    private String oldPassword;
    private String newPassword;
}
