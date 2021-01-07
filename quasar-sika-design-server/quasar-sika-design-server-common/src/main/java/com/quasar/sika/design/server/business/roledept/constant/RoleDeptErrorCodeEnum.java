package com.quasar.sika.design.server.business.roledept.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 角色和部门关联表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-08 00:03:07
 */
@Getter
@AllArgsConstructor
public enum RoleDeptErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ROLEDEPT_ERROR_CODE_ENUM("ROLEDEPT_000001", "错误枚举示例")
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
