package com.quasar.sika.design.server.business.rolemenu.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 角色菜单表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2020-12-26 16:59:43
 */
@Getter
@AllArgsConstructor
public enum RoleMenuErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ROLEMENU_ERROR_CODE_ENUM("ROLEMENU_000001", "错误枚举示例")
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
