package com.quasar.sika.design.server.common.auth.pojo.response;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthResponse {
    private UserDTO user;
    private String token;
    public AuthResponse build() {
        this.token = ShiroUtils.getSessionId();
        return this;
    }

    public static AuthResponse success(UserDTO userDTO) {
        return new AuthResponse().setUser(userDTO).build();
    }
    public static AuthResponse success() {
        return success(null);
    }
}
