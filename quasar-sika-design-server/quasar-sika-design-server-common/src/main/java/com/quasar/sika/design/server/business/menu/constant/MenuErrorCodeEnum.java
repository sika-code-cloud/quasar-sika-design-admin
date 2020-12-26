package com.quasar.sika.design.server.business.menu.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 菜单表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:26
 */
@Getter
@AllArgsConstructor
public enum MenuErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    MENU_ERROR_CODE_ENUM("MENU_000001", "错误枚举示例")
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
