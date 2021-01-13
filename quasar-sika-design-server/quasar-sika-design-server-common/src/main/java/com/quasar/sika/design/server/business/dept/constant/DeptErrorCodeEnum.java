package com.quasar.sika.design.server.business.dept.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 部门表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:19
 */
@Getter
@AllArgsConstructor
public enum DeptErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    DEPT_ERROR_CODE_ENUM("DEPT_000001", "错误枚举示例")
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
