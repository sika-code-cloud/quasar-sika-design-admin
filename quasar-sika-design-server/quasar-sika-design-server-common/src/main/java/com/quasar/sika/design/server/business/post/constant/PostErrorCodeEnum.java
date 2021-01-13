package com.quasar.sika.design.server.business.post.constant;

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 岗位信息表 错误枚举类
 * </p>
 *
 * @author daiqi
 * @since 2021-01-07 23:35:42
 */
@Getter
@AllArgsConstructor
public enum PostErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    POST_ERROR_CODE_ENUM("POST_000001", "错误枚举示例")
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
