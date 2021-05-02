package com.quasar.sika.design.server.business.useronline.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 在线用户记录 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:28
 */
@Getter
@AllArgsConstructor
public enum UserOnlineErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    USERONLINE_ERROR_CODE_ENUM("USERONLINE_000001", "错误枚举示例")
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
