package com.quasar.sika.design.server.business.mailtemplate.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 邮件模板表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-02 21:24:06
 */
@Getter
@AllArgsConstructor
public enum MailTemplateErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    MAILTEMPLATE_ERROR_CODE_ENUM("MAILTEMPLATE_000001", "错误枚举示例")
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
