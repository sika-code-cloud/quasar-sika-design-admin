package com.quasar.sika.design.server.business.operlog.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 操作日志记录 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:30
 */
@Getter
@AllArgsConstructor
public enum OperLogErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    OPERLOG_ERROR_CODE_ENUM("OPERLOG_000001", "错误枚举示例")
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
