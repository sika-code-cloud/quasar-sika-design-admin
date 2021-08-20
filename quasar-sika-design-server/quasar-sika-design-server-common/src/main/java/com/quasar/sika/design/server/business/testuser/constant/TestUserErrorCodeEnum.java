package com.quasar.sika.design.server.business.testuser.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 测试用户表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:44:03
 */
@Getter
@AllArgsConstructor
public enum TestUserErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    TESTUSER0_ERROR_CODE_ENUM("TESTUSER0_000001", "错误枚举示例")
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
