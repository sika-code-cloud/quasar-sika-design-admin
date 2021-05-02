package com.quasar.sika.design.server.business.role.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 角色信息表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:53
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
