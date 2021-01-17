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
public enum MailCodeEnum implements TypeEnumInf<Integer> {
    /**
     * 邮箱授权码编码
     */
    BIND_OAUTH_USER(10, "MC_00001","授权用户绑定"),
    /**
     * 邮箱授权码编码
     */
    USER_REGISTER(10, "MC_00002","注册用户"),

    FORGET_PASSWORD(10, "MC_00003","找回密码"),
    ;
    private final Integer type;
    private final String code;
    private final String desc;


}