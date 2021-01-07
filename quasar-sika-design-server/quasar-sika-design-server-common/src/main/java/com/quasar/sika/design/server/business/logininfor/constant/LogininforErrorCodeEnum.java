package com.quasar.sika.design.server.business.logininfor.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 系统访问记录 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:54
 */
@Getter
@AllArgsConstructor
public enum LogininforErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    LOGININFOR_ERROR_CODE_ENUM("LOGININFOR_000001", "错误枚举示例")
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
