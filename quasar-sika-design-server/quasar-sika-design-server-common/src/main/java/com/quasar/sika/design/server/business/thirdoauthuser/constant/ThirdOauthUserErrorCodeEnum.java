package com.quasar.sika.design.server.business.thirdoauthuser.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 第三方授权用户表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-01 23:24:30
 */
@Getter
@AllArgsConstructor
public enum ThirdOauthUserErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    THIRDOAUTHUSER_ERROR_CODE_ENUM("THIRDOAUTHUSER_000001", "错误枚举示例")
    ;

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
}
