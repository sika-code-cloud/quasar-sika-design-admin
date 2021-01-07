package com.quasar.sika.design.server.business.config.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 参数配置表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:02
 */
@Getter
@AllArgsConstructor
public enum ConfigErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    CONFIG_ERROR_CODE_ENUM("CONFIG_000001", "错误枚举示例")
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
