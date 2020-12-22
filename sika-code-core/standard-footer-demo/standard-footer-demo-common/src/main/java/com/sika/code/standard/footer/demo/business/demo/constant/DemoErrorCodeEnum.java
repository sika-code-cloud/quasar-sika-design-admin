package com.sika.code.standard.footer.demo.business.demo.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 示例表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-22 12:07:27
 */
@Getter
@AllArgsConstructor
public enum DemoErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    DEMO_ERROR_CODE_ENUM("DEMO_000001", "错误枚举示例")
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
