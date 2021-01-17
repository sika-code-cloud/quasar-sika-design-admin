package com.quasar.sika.design.server.common.auth.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sikadai
 * @Description: 认证异常码
 * @date 2021/1/217:05
 */
@Getter
@AllArgsConstructor
public enum AuthErrorCodeEnum implements BaseErrorCode {
    /**
     * 认证错误码枚举
     */
    OAUTH_USER_NOT_AUTH("OAUTH_USER_100001", "授权用户没有认证");
    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}