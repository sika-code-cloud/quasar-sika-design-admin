package com.quasar.sika.design.server.common.mail.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sikadai
 * @Description: 邮箱code枚举
 * @date 2021/1/217:05
 */
@Getter
@AllArgsConstructor
public enum MailCodeEnum implements TypeEnumInf<String> {
    /**
     * 邮箱授权码编码
     */
    BIND_OAUTH_USER("BIND_OAUTH_USER_00001","授权用户绑定验证码"),
    ;
    private final String type;
    private final String desc;


}