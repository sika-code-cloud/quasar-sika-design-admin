package com.quasar.sika.design.server.business.chinacity.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 参数配置表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-20 13:01:39
 */
@Getter
@AllArgsConstructor
public enum ChinaCityErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    CHINACITY_ERROR_CODE_ENUM("CHINACITY_000001", "错误枚举示例")
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
