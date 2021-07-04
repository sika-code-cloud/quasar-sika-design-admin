package com.quasar.sika.design.server.business.testorder.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 测试订单表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-07-03 15:41:28
 */
@Getter
@AllArgsConstructor
public enum TestOrderErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    TESTORDER_ERROR_CODE_ENUM("TESTORDER_000001", "错误枚举示例")
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
