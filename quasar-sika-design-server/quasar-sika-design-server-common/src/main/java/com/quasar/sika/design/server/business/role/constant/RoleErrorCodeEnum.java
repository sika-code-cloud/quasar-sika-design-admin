package com.quasar.sika.design.server.business.role.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 角色表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:17
 */
@Getter
@AllArgsConstructor
public enum RoleErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ROLE_ERROR_CODE_ENUM("ROLE_000001", "错误枚举示例")
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
