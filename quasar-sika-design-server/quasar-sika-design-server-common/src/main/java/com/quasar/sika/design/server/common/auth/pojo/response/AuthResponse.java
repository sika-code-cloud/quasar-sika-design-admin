package com.quasar.sika.design.server.common.auth.pojo.response;

import com.quasar.sika.design.server.business.user.pojo.dto.UserDTO;
import com.quasar.sika.design.server.common.auth.constant.AuthErrorCodeEnum;
import com.quasar.sika.design.server.common.shiro.util.ShiroUtils;
import com.sika.code.basic.util.BaseUtil;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthResponse {
    private UserDTO user;
    private String token;
    private String errorCode;
    private String message;
    public AuthResponse build() {
        this.token = ShiroUtils.getSessionId();
        if (BaseUtil.isNull(user)) {
            this.user = ShiroUtils.getUserInfo();
        }
        return this;
    }

    public Boolean getSuccess() {
        return BaseUtil.isNull(this.errorCode);
    }

    public static AuthResponse success(UserDTO userDTO) {
        return new AuthResponse().setUser(userDTO).build();
    }
    public static AuthResponse success() {
        return success(null);
    }
    public static AuthResponse error(AuthErrorCodeEnum errorCodeEnum) {
        return new AuthResponse()
                .setErrorCode(errorCodeEnum.getCode())
                .setMessage(errorCodeEnum.getMessage())
                .build();
    }
}
