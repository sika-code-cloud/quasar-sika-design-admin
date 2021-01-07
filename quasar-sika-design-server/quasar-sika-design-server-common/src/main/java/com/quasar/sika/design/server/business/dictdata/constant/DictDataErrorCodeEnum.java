package com.quasar.sika.design.server.business.dictdata.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 字典数据表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:34:31
 */
@Getter
@AllArgsConstructor
public enum DictDataErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    DICTDATA_ERROR_CODE_ENUM("DICTDATA_000001", "错误枚举示例")
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
